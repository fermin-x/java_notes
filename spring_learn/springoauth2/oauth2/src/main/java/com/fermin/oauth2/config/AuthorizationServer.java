package com.fermin.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
//开启鉴权服务
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserDetailService authUserDetailService;

    /**
     * 用来配置令牌端点的安全约束.
     * 认证服务器最终是以api接口的方式对外提供服务（校验合法性并生成令牌、校验令牌等）
     * 那么，以api接口方式对外的话，就涉及到接口的访问权限，我们需要在这里进行必要的配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        // 相当于打开endpoints 访问接口的开关，这样的话后期我们能够访问该接口
        security
                // 允许客户端表单认证
                .allowFormAuthenticationForClients()
                // 开启端口/oauth/token_key的访问权限（允许）
                .tokenKeyAccess("permitAll()")
                // 开启端口/oauth/check_token的访问权限（允许）
                .checkTokenAccess("permitAll()");
    }

    /**
     * 用来配置客户端详情服务(ClientDetailsService)，客户端详情信息在 这里进行初始化，
     * 能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     * 比如client_id，secret
     * 当前这个服务就如同QQ平台，拉勾网作为客户端需要qq平台进行登录授权认证等，提前需要到QQ平台注册，QQ平台会给拉勾网
     * 颁发client_id等必要参数，表明客户端是谁
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);

        // 从内存中加载客户端详情
//        clients.inMemory()// 客户端信息存储在什么地方，可以在内存中，可以在数据库里
//                .withClient("client_test")         // 添加一个client配置,指定其client_id
//                .secret("test123456")              // 指定客户端的密码/安全码
//                .resourceIds("order", "auth")      // 指定客户端所能访问资源id清单，此处的资源id是需要在具体的资源服务器上也配置一样
//                // 认证类型/令牌颁发模式，可以配置多个在这里，但是不一定都用，具体使用哪种方式颁发token，需要客户端调用的时候传递参数指定
//                // authorization_code,password,client_credentials,implicit,refresh_token
//                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .redirectUris("http://www.baidu.com")
//                // 客户端的权限范围，此处配置为all全部即可
//                .scopes("all");

        //客户端信息配置从数据读取
        clients.withClientDetails(clientDetailsService());

    }

    public ClientDetailsService clientDetailsService() {
        //配置客户端存储到db
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        //设置密码加密工具
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 用来配置令牌(token)的存放和访问端点和令牌服务(token services)
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints
                // 指定token的存储方法， TODO 配置令牌管理服务后可以注释
//                .tokenStore(tokenStore())
                // token服务的一个描述，可以认为是token生成细节的描述，比如有效时间多少等
                .tokenServices(authorizationServerTokenService())
                // 指定认证管理器，随后注入一个到当前类使用即可
                .authenticationManager(authenticationManager)
                // 授权码服务
                .authorizationCodeServices(authorizationCodeServices())
                //用户数据服务
                .userDetailsService(authUserDetailService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 授权码模式时，授权码的存储服务
     *
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);//设置授权码模式的授权码如何存取
    }

    /**
     * 关于 TokenStore
     * InMemoryTokenStore
     * 默认采用，它可以完美的工作在单服务器上(即访问并发量 压力不大的情况下，在失败的时候不会进行备份)，
     * 大多数的项目都可以使用这个版本的实现来进行 尝试， 你可以在开发的时候使用它来进行管理，因为不会被保存到磁盘中，所以更易于调试。
     * <p>
     * JdbcTokenStore
     * 这是一个基于JDBC的实现版本，令牌会被保存进关系型数据库。使用这个版本的实现 时，
     * 你可以在不同的服务器之间共享令牌信息，使用这个版本的时候请注意把"spring-jdbc"这个依赖加入到你的 classpath当中。
     * <p>
     * JwtTokenStore 这个版本的全称是 JSON Web Token(JWT)，
     * 它可以把令牌相关的数 据进行编码(因此对于后端服务来说，它不需要进行存储，这将是一个重大优势)，
     * 缺 点就是这个令牌占用的空间会比较大，如果你加入了比较多用户凭证信息， JwtTokenStore 不会保存任何数据。
     * 该方法用于创建tokenStore对象（令牌存储对象）
     * <p>
     * 配置token以什么形式存储和访问
     */
    public TokenStore tokenStore() {
        //token 存储在内存中
//        return new InMemoryTokenStore();
        //token 存储在数据库中
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 该方法用户获取一个token服务对象（该对象描述了token有效期等信息）
     */
    public AuthorizationServerTokenServices authorizationServerTokenService() {
        // 使用默认实现
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //支持使用refreshtoken刷新access token
        defaultTokenServices.setSupportRefreshToken(true);
        //允许重复使用refreshtoken
        defaultTokenServices.setReuseRefreshToken(false);

        defaultTokenServices.setTokenStore(tokenStore());
        //客户端详情配置，在token生成的时候需要读取客户端的配置数据
        defaultTokenServices.setClientDetailsService(clientDetailsService());
        // 设置令牌有效时间（一般设置为1个小时）access_token就是我们请求资源需要携带的令牌
        defaultTokenServices.setAccessTokenValiditySeconds(60 * 60 * 1);
        // 设置刷新令牌的有效时间 3 天
        defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);

        //令牌增强 jwt转换
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
//        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);

        return defaultTokenServices;
    }
}
