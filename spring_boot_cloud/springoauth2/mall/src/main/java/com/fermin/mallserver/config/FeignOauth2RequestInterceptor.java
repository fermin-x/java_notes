package com.fermin.mallserver.config;

import com.fermin.mallserver.utils.JJWTUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * SpringCloud 中通过 Feign 调用其他服务，当服务使用 Oauth2 授权的时候，
 * Feign 默认并不会将认证的 TOKEN 带在请求的 Header 中，需要手动实现传递 TOKEN 的配置
 */
@Configuration
public class FeignOauth2RequestInterceptor implements RequestInterceptor {
    private final String INNER_AUTHORIZATION_HEADER = "INNER";
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_TOKEN_TYPE = "Bearer";

    // TODO 后续这个可以放在配置文件中，每个系统的签发者不一样
    private final String ISSUE = "MALLTEST";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //统一添加内部调用header
        requestTemplate.header(INNER_AUTHORIZATION_HEADER, JJWTUtils.createJWT(ISSUE));
        //传递调用者TOKEN
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
        }
    }
}
