package org.activiti.examples.req;

/**
 * @author wangkai
 * @since JDK8
 */
public class LoginReq {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public LoginReq setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginReq setPassword(String password) {
        this.password = password;
        return this;
    }
}
