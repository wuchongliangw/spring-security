package com.meamei.util;

import com.meamei.filter.SmsCodeAuthenticationFilter;
import com.meamei.security.CustomAuthenticationFailureHandler;
import com.meamei.security.CustomAuthenticationSuccessHandler;
import com.meamei.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author mm013
 * @create 2020-05-11 17:26:20
 * @description:
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

    //    SmsCodeAuthenticationFilter authenticationFilter = new SmsCodeAuthenticationFilter();
   //     authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        // 登录成功处理
    //    authenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);

        // 登录失败处理
     //   authenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

     //   http.authenticationProvider(smsCodeAuthenticationProvider);
              //  .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
