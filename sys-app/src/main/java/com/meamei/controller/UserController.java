package com.meamei.controller;

import com.meamei.baseConfig.RestResponse;
import com.meamei.baseEntity.model.User;
import com.meamei.baseService.service.UserService;
import com.meamei.model.UserDetailImpl;
import com.meamei.util.JwtTokenUtil;
import com.meamei.util.SmsCodeAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.meamei.controller.BaseController.SUCCESS_MESSAGE;


/**
 * @author mm013
 * @create 2020-04-15 17:42:09
 * @description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager author;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 注册
     *
     * @return
     */
    @GetMapping("/user/register")
    public RestResponse register(String username, String password) {
        User user = new User();
        user.setAccount(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsLocked(Boolean.FALSE);
        user.setLoginFailureCount(0);
        userService.save(user);
        return RestResponse.success(SUCCESS_MESSAGE);
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/user/login")
    public RestResponse login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = author.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenUtil.generateToken(username);
        return RestResponse.success(token);
    }

    /**
     * 短信验证码登录
     *
     * @param telephone 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/user/sms/login")
    public RestResponse smsLogin(String telephone, String password) {
        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(telephone);
        Authentication authenticate = author.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenUtil.generateToken(telephone);
        return RestResponse.success(token);
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/user/getInfo")
    public RestResponse getInfo(@RequestParam Long userId) {
        User user = userService.find(userId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImpl users = (UserDetailImpl) authentication.getPrincipal();
        String account = users.getUsername();
        return RestResponse.success(user);
    }

}
