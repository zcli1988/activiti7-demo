package org.activiti.examples.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangkai
 * @since JDK8
 */
public interface RBACService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
