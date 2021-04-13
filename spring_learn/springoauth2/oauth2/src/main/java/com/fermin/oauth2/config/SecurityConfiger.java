package com.fermin.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * 该配置类，主要处理用户名和密码的校验等事宜
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiger extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserDetailService authUserDetailService;

    /**
     * 注册一个认证管理器对象到容器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码编码对象
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //方便测试密码不进行加密处理
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }


    /**
     * 处理用户名和密码验证事宜
     * 1）客户端传递username和password参数到认证服务器
     * 2）一般来说，username和password会存储在数据库中的用户表中
     * 3）根据用户表中数据，验证当前传递过来的用户信息的合法性
     * <p>
     * TODO
     * 用户的数据从数据库读取 {@link com.fermin.oauth2.config.AuthUserDetailService}
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在这个方法中就可以去关联数据库了，当前我们先把用户信息配置在内存中
        // 实例化一个用户对象(相当于数据表中的一条用户记录)
//        auth.inMemoryAuthentication()
//                //注意roles authorities 在后面的才会生效。
//                //.roles("r1","r2").authorities("r3","r4")  roles无效 authorities有效
//                //两种选一种
//                //一般我们简单系统只需根据authorities权限配置就行
//                .withUser("admin").password(passwordEncoder().encode("123456")).roles("r1","r2").authorities("r3","r4")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("123456")).authorities("r3","r4").roles("r1","r2");

        auth.userDetailsService(authUserDetailService);
    }
}
