package com.xuliugen.springboot.config.security;

import com.xuliugen.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现自己的UserDetailsService和PermissionEvaluator，分别用于自定义Principle, Authority和Permission
 * Created by xuliugen on 2017/3/28.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //这里获取到的数据，并不关心数据来自哪里
        com.xuliugen.springboot.domain.User user = userMapper.selectByUserName(username);
        List<GrantedAuthority> authorities = null;
        if (user != null) {
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_SPITTEER"));//创建权限列表,这里加上权限
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
