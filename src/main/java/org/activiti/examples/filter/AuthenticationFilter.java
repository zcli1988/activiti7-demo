package org.activiti.examples.filter;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangkai
 * @since JDK8
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final static String HEADER_NAME = "Authorization";
    private final static String Authorization_Head = "Bearer ";

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_NAME);
        if (header != null && header.startsWith(Authorization_Head)) {
            final String token = header.substring(Authorization_Head.length());

            if (token != null && redisTemplate.hasKey(token)) {
                String username = redisTemplate.opsForValue().get(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    //可以校验token和username是否有效，目前由于token对应username存在redis，都以默认都是有效的
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //工作流授权
                    org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
