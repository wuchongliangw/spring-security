package com.meamei.util;

import com.meamei.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author mm013
 * @create 2020-05-11 10:02:25
 * @description:
 */
@Configuration
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    public UserDetailServiceImpl getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
