package com.xuliugen.springboot.mapper;

import com.xuliugen.springboot.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xuliugen on 2017/3/26.
 */
@Component
public interface UserMapper {

    /**
     * 使用注解的方式，这样的话就不需要设置mapper.xml文件了
     * @param username
     * @param password
     * @return
     */
    //@Insert("INSERT INTO tb_user(username, password) VALUES(#{username},#{password})")
    //public int insert(@Param("username") String username, @Param("password") String password);

    public int insert(@Param("id") String id, @Param("username") String username, @Param("password") String password);

    public int deleteByPrimaryKey(@Param("id") String id);

    public int selectByPrimaryKey(@Param("id") String id);

    public List<User> listUsers();
}
