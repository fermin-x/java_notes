package com.fermin.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "order";

//    @Autowired
//    public TokenStore tokenStore;

    // 引入的bean是为了解决no bean resolver registered的问题
    // https://github.com/spring-projects/spring-security-oauth/issues/730#issuecomment-219480394
    @Autowired
    private DefaultWebSecurityExpressionHandler expressionHandler;

    @Bean
    public DefaultWebSecurityExpressionHandler OAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
        resources
                //RESOURCE_ID 配置需要和 client_detail 中配置的id一至
                .resourceId(RESOURCE_ID)
//                .tokenStore(tokenStore)
                //资源服务器通过访问授权服务器 /oauth/check_token 端点解析令牌需要使用
                .tokenServices(tokenServices())
                //token信息不需要记录在session中
                .stateless(true);
    }

    /**
     * 通过访问授权服务器解析令牌-适用 JDBC、内存存储
     * 资源服务器通过访问授权服务器 /oauth/check_token 端点解析令牌需要使用
     *
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        //TODO 实际配置oauth服务的地址,以及客户端用户名和密码等
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        remoteTokenServices.setClientId("client_test");
        remoteTokenServices.setClientSecret("test123456");

        return remoteTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();

        http.authorizeRequests().anyRequest().authenticated();
        
//        http.authorizeRequests().anyRequest().access("@authService.canAccess(request, authentication)");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
