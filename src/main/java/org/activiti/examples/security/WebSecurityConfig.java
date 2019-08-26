package org.activiti.examples.security;

import com.alibaba.fastjson.JSON;
import org.activiti.examples.context.GlobalResult;
import org.activiti.examples.context.LocalUtil;
import org.activiti.examples.filter.AuthenticationFilter;
import org.activiti.examples.resp.SuccessResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import javax.annotation.Resource;

/**
 * spring security 配置
 *
 * @author wangkai
 * @since JDK8
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")

                .and()
                .addFilterBefore(new AuthenticationFilter(userDetailsService, redisTemplate), AnonymousAuthenticationFilter.class)

                .logout()
                .addLogoutHandler((request, response, authentication) -> redisTemplate.delete(LocalUtil.getSession()))
                .logoutSuccessHandler((request, response, authentication) -> response.getWriter().print(JSON.toJSONString(new GlobalResult(new SuccessResp()))))

                .and()
                .exceptionHandling()
                .accessDeniedHandler(((request, response, accessDeniedException) -> response.getWriter().print(JSON.toJSONString(new GlobalResult(new SuccessResp("无访问权限"))))))

                .and()
                .rememberMe().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
