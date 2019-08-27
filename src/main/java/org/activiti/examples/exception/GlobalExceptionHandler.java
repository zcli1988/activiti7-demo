package org.activiti.examples.exception;

import org.activiti.examples.context.GlobalResult;
import org.springframework.security.authentication.BadCredentialsException;
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
            return new GlobalResult(GlobalResult.CODE_FAILED, "用户不存在");
        }
        if (e instanceof CredentialException) {
            return new GlobalResult(GlobalResult.CODE_FAILED, "证书错误");
        }
        if (e instanceof AccessDeniedException) {
            return new GlobalResult(GlobalResult.CODE_FAILED, "不允许访问");
        }
        if (e instanceof BadCredentialsException) {
            return new GlobalResult(GlobalResult.CODE_FAILED, "密码错误");
        }
        return new GlobalResult(GlobalResult.CODE_FAILED, e.getMessage());
    }

}
