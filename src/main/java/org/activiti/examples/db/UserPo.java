package org.activiti.examples.db;

import java.io.Serializable;

/**
 * @author wangkai
 * @since JDK8
 */
public class UserPo implements Serializable {
    private static final long serialVersionUID = -3628285896079075179L;
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public UserPo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserPo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserPo setPassword(String password) {
        this.password = password;
        return this;
    }

}
