package com.meamei.security;

import com.alibaba.fastjson.JSON;
import com.meamei.baseConfig.RestResponse;
import com.meamei.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mm013
 * @create 2020-04-22 15:32:55
 * @description:  自定义登录成功返回信息
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().flush();
        String token = jwtTokenUtil.generateToken(request.getParameter("telephone"));
        response.getWriter().write(JSON.toJSONString(RestResponse.success(token)));
    }
}
