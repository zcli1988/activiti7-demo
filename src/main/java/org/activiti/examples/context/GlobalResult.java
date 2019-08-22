package org.activiti.examples.context;

import org.activiti.examples.resp.SuccessResp;

import java.io.Serializable;

/**
 * @author wangkai
 * @since JDK8
 */
public class GlobalResult implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3040488295566534468L;
    /**
     * 响应成功的代码，0
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * 响应失败的代码，非0
     */
    public static final int CODE_FAILED = -1;

    private int code;
    private String msg;
    private Object result;

    public GlobalResult() {
    }

    public GlobalResult(Object result) {
        this.code = CODE_SUCCESS;
        this.result = result;
    }

    public GlobalResult(SuccessResp successResp) {
        this.code = CODE_SUCCESS;
        this.msg = successResp.getMsg();
    }

    public GlobalResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
