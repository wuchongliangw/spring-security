package com.meamei.controller;

import com.meamei.baseConfig.RestResponse;
import com.meamei.baseEntity.model.User;
import com.meamei.model.UserDetailImpl;
import com.meamei.util.SmsCodeAuthenticationToken;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    /**
     * 短信验证码登录
     *
     * @param telephone 用户名
     * @return
     */
    @PostMapping("/sms/login")
    public RestResponse smsLogin(String telephone, HttpServletRequest request) {
        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(telephone);
        Authentication authenticate = author.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);


        String header = request.getHeader(AUTHORIZATION);
        if (header == null) {
            return null;
        }

        header = header.trim();
        if (!StringUtils.startsWithIgnoreCase(header, "Basic")) {
            return null;
        }

        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        String[] split = token.split(":");


        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(split[0]);

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, split[0], clientDetails.getScope(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authenticate);

        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        return RestResponse.success(accessToken.getValue());
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
