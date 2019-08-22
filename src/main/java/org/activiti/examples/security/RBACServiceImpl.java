package org.activiti.examples.security;

import org.activiti.examples.db.UserPo;
import org.activiti.examples.mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangkai
 * @since JDK8
 */
@Component("rbacService")
public class RBACServiceImpl implements RBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            String userName = ((UserDetails) principal).getUsername();
            UserPo userPo = userMapper.selectByUsername(userName);
            List<String> urls = userMapper.selectPermissionsByUserId(userPo.getId());
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
