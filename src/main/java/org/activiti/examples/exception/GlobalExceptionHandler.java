package org.activiti.examples.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.CredentialException;
import java.nio.file.AccessDeniedException;

/**
 * @author wangkai
 * @since JDK8
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Object exceptionHandler(Exception e) {
        e.printStackTrace();
        if (e instanceof UsernameNotFoundException) {
            return "用户不存在";
        }
        if (e instanceof CredentialException) {
            return "密码错误";
        }
        if (e instanceof AccessDeniedException) {
            return "不允许访问";
        }
        return e.getMessage();
    }

}
