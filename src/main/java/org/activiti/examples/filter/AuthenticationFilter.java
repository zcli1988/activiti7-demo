package org.activiti.examples.filter;

import org.activiti.examples.Config;
import org.activiti.examples.context.LocalUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 用户认证、授权Filter
 *
 * @author wangkai
 * @since JDK8
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private RedisTemplate<String, String> redisTemplate;

    public AuthenticationFilter(UserDetailsService userDetailsService, RedisTemplate<String, String> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = LocalUtil.getSession();
        if (token != null) {
            String username = redisTemplate.opsForValue().get(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                redisTemplate.opsForValue().set(token, username, Config.expire, TimeUnit.MINUTES);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
                //工作流授权
                org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
            }
        }
        filterChain.doFilter(request, response);
    }
}
