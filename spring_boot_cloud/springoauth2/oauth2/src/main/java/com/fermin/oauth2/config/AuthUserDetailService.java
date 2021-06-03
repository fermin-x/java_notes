package com.fermin.oauth2.config;

import com.fermin.oauth2.DO.AuthUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("authUserDetailService")
public class AuthUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 实际情况下需要根据 {@link username} 去数据库动态查询
        AuthUserDetails userDetails = new AuthUserDetails();
        userDetails.setUserId(10000L);
        userDetails.setUsername("admin");
        userDetails.setPassword("123456");
        //模拟权限
        Set<GrantedAuthority> dbAuthsSet = new HashSet<>();
        dbAuthsSet.add(new SimpleGrantedAuthority("test"));
        userDetails.setAuthorities(dbAuthsSet);
        return userDetails;
    }
}
