package org.activiti.examples.security;

import com.alibaba.fastjson.JSON;
import org.activiti.examples.context.GlobalResult;
import org.activiti.examples.resp.SuccessResp;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录成功后的Handler，用于删除redis缓存及记录日志等操作
 *
 * @author wangkai
 * @since JDK8
 */
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf8");

        response.getWriter().print(JSON.toJSONString(new GlobalResult(new SuccessResp())));
    }

}
