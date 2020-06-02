package com.meamei.filter;

import com.meamei.baseConfig.RestResponse;
import com.meamei.service.UserDetailServiceImpl;
import com.meamei.util.JwtTokenUtil;
import com.meamei.util.SmsCodeAuthenticationToken;
import com.meamei.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mm013
 * @create 2020-04-27 18:00:53
 * @description:
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    private String tokenHeader = "Authorization";
    private String tokenHead = "Bearer";

    // 忽略请求拦截
    private static final String[] IGNORE_URLS = new String[]{
            "/user/sms/login",
            "/authentication/telephone"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (isIgnoreUrl(request.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else {
            String jwt = resolveToken(request);
            if (StringUtils.isNotBlank(jwt) && jwtTokenUtil.validateToken(jwt)) {
                String username = jwtTokenUtil.getUserNameFromToken(jwt);
                LOGGER.info("checking username:{}", username);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailService.loadUserByUsername(username);
                    SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else {
                errorResponse(response, RestResponse.error("校验token错误"));
                return;
            }
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 从请求头中获取token
     *
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(tokenHeader);
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(tokenHeader.length());
        }
        return null;
    }

    /**
     * 请求的url是否忽略拦截
     *
     * @param httpRequestUrl 请求url
     * @return
     */
    private boolean isIgnoreUrl(String httpRequestUrl) {
        for (String url : IGNORE_URLS) {
            if (httpRequestUrl.contains(url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输出登录拦截错误信息
     *
     * @param httpServletResponse
     * @param restResponse
     * @throws IOException
     */
    private void errorResponse(HttpServletResponse httpServletResponse, RestResponse restResponse) throws IOException {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        String json = JsonUtils.toJson(restResponse);
        assert json != null;
        httpServletResponse.getWriter().write(json);
    }
}
