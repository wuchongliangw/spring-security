package com.meamei.filter;

import com.meamei.util.SmsCodeAuthenticationToken;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mm013
 * @create 2020-05-11 9:38:31
 * @description: 短信登录
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SPRING_SECURITY_FORM_TELEPHONE_KEY = "telephone";

    private String telephoneParameter = SPRING_SECURITY_FORM_TELEPHONE_KEY;
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/telephone", "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String telephone = obtainTelephone(request);

        if (telephone == null) {
            telephone = "";
        }

        telephone = telephone.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(telephone);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 获取手机号
     */
    @Nullable
    protected String obtainTelephone(HttpServletRequest request) {
        return request.getParameter(telephoneParameter);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     * set
     */
    protected void setDetails(HttpServletRequest request,
                              SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the username from the login
     * request.
     *
     * @param telephoneParameter the parameter name. Defaults to "username".
     */
    public void setTelephoneParameter(String telephoneParameter) {
        Assert.hasText(telephoneParameter, "Telephone parameter must not be empty or null");
        this.telephoneParameter = telephoneParameter;
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getTelephoneParameter() {
        return telephoneParameter;
    }
}
