package com.xuliugen.springboot.service;

import com.xuliugen.springboot.domain.User;

import java.util.List;

/**
 * Created by xuliugen on 2017/3/26.
 */
public interface UserService {

    public int register(String username, String password);

    List<User> listUser();
}
