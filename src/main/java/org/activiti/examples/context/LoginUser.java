package org.activiti.examples.context;

import java.io.Serializable;

/**
 * @author wangkai
 * @since JDK8
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1895373015898536592L;
    private Integer id;
    private String username;
    private Integer master;
    private Integer companyId;

    public Integer getId() {
        return id;
    }

    public LoginUser setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getMaster() {
        return master;
    }

    public LoginUser setMaster(Integer master) {
        this.master = master;
        return this;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public LoginUser setCompanyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }
}
