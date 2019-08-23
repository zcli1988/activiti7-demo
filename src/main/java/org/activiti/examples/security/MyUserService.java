package org.activiti.examples.security;

import org.activiti.examples.db.RolePo;
import org.activiti.examples.db.UserPo;
import org.activiti.examples.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkai
 * @since JDK8
 */
@Service
public class MyUserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPo user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }
        RolePo rolePo = userMapper.selectRoleByUserId(user.getId());
        //用户权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(1);
        if (StringUtils.isNotBlank(rolePo.getCode())) {
            authorities.add(new SimpleGrantedAuthority(rolePo.getCode().trim()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}