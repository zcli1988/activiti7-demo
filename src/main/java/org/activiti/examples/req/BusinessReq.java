package org.activiti.examples.req;

/**
 * @author wangkai
 * @since JDK8
 */
public class BusinessReq {
    private int applyUserId;
    private String content;

    public int getApplyUserId() {
        return applyUserId;
    }

    public BusinessReq setApplyUserId(int applyUserId) {
        this.applyUserId = applyUserId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BusinessReq setContent(String content) {
        this.content = content;
        return this;
    }
}
