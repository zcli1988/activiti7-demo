package org.activiti.examples.resp;

import java.io.Serializable;

/**
 * 统一的操作成功返回对象
 *
 * @author wangkai
 * @since JDK8
 */
public class SuccessResp implements Serializable {
    private static final long serialVersionUID = 7804680842114455720L;
    private String msg = "成功";

    public SuccessResp() {
    }

    public SuccessResp(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public SuccessResp setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
