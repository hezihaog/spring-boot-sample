package com.xuliugen.springboot.service;

import com.xuliugen.springboot.domain.User;
import com.xuliugen.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by xuliugen on 2017/3/26.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(String username, String password) {
        return userMapper.insert(UUID.randomUUID().toString().replace("-", "").toUpperCase(), username, password);
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUsers();
    }
}
