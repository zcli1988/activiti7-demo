package org.activiti.examples.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 由于系统前后端分离部署，请求会跨域，该过滤器用来解决系统跨域问题
 *
 * @author wangkai
 * @since JDK8
 */
public class MonitorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(200);
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Allow-Methods", "HEAD,GET,PUT,OPTIONS,PATCH,DELETE,POST");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
