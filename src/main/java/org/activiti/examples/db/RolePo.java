package org.activiti.examples.db;

import java.io.Serializable;

/**
 * @author wangkai
 * @since JDK8
 */
public class RolePo implements Serializable {

    private static final long serialVersionUID = -312937393722534535L;

    private int id;
    private String code;

    public int getId() {
        return id;
    }

    public RolePo setId(int id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RolePo setCode(String code) {
        this.code = code;
        return this;
    }
}
