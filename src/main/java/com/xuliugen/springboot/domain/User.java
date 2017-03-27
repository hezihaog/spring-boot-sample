package com.xuliugen.springboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by xuliugen on 2017/3/26.
 */
public class User {

    private String id;

    @NotNull(message = "username不为空")
    @Size(min = 10, max = 12)
    private String username;

    @NotNull(message = "password")
    @Size(min = 10, max = 12)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 使用Apache Common Lang包来实现equals()和hashCode()方法
     * @param obj 需要比较的对象
     * @return 是否相同
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "id");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }
}
