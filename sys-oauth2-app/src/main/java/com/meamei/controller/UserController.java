package com.meamei.controller;

import com.meamei.baseConfig.RestResponse;
import com.meamei.baseEntity.model.User;
import com.meamei.model.UserDetailImpl;
import com.meamei.util.SmsCodeAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mm013
 * @create 2020-06-08 10:52:22
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private AuthenticationManager author;

    /**
     * 短信验证码登录
     *
     * @param telephone 用户名
     * @return
     */
    @PostMapping("/sms/login")
    public RestResponse smsLogin(String telephone) {
        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(telephone);
        Authentication authenticate = author.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return RestResponse.success(SUCCESS_MESSAGE);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getInfo")
    public Object getInfo(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImpl users = (UserDetailImpl) authentication.getPrincipal();
        String account = users.getUsername();

        return RestResponse.success(account);
    }
}
