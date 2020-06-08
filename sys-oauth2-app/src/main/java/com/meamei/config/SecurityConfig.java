package com.meamei.config;

import com.meamei.service.UserDetailServiceImpl;
import com.meamei.util.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mm013
 * @create 2020-04-15 18:27:12
 * @description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        return new UserDetailServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 添加一个判断短信验证码是否正确的过滤器

        // http.addFilterBefore();
        http
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/sms/login").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/authentication/telephone").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable().apply(smsCodeAuthenticationSecurityConfig);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
       // http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
