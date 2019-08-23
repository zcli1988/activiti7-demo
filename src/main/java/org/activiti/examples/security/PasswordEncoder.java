package org.activiti.examples.security;

import org.activiti.examples.util.MD5Util;

/**
 * 用户密码编码、匹配器
 *
 * @author wangkai
 * @since JDK8
 */
public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.md5((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.md5((String) rawPassword));
    }
}
