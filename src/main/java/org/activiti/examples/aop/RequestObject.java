package org.activiti.examples.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义请求对象注解，用于控制层打印日志时，获取对象格式化类型
 *
 * @author wangkai
 * @since JDK8
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestObject {
}
