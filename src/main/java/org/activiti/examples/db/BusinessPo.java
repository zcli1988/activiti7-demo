package org.activiti.examples.db;

import java.util.Date;

/**
 * @author wangkai
 * @since JDK8
 */
public class BusinessPo {
    private Integer id;
    private Integer apply_user_id;
    private String content;
    private Integer pass;
    private Integer createUser;
    private Date createTime;
    private Integer updateUser;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public BusinessPo setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getApply_user_id() {
        return apply_user_id;
    }

    public BusinessPo setApply_user_id(Integer apply_user_id) {
        this.apply_user_id = apply_user_id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BusinessPo setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getPass() {
        return pass;
    }

    public BusinessPo setPass(Integer pass) {
        this.pass = pass;
        return this;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public BusinessPo setCreateUser(Integer createUser) {
        this.createUser = createUser;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BusinessPo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public BusinessPo setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BusinessPo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
