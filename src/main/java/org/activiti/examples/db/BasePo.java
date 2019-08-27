package org.activiti.examples.db;

import java.io.Serializable;
import java.util.Date;

/**
 * 提供了所有数据表共同的属性，所有数据表对应实体都需要继承该类
 *
 * @author wangkai
 * @since JDK8
 */
public class BasePo {
    private Integer createUser;
    private Date createTime;
    private Integer updateUser;
    private Date updateTime;

    public Integer getCreateUser() {
        return createUser;
    }

    public BasePo setCreateUser(Integer createUser) {
        this.createUser = createUser;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BasePo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public BasePo setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BasePo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

}
