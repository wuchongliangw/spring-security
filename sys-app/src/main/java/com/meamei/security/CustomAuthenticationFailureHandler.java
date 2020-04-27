package com.meamei.security;

import com.alibaba.fastjson.JSON;
import com.meamei.baseConfig.RestResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mm013
 * @create 2020-04-16 17:06:43
 * @description:  自定义登录失败返回信息
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // 自定义登录失败返回信息
        if (exception instanceof BadCredentialsException) {
            response.getWriter().write(JSON.toJSONString(RestResponse.error("用户名或密码错误")));
        }
        // 其他错误...
        response.getWriter().flush();
    }
}
