package com.meamei.security;

import com.alibaba.fastjson.JSON;
import com.meamei.baseConfig.RestResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mm013
 * @create 2020-04-16 14:41:07
 * @description:
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(JSON.toJSONString(RestResponse.error(RestResponse.CODE_NOT_AUTHORIZATION, RestResponse.MESSAGE_NOT_AUTHORIZATION)));
        response.getWriter().flush();
    }
}
