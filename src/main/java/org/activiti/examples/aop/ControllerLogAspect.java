package org.activiti.examples.aop;

import com.alibaba.fastjson.JSON;
import org.activiti.examples.context.LocalUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 控制层日志切面，用于打印请求参数、响应内容、响应时间等
 *
 * @author wangkai
 * @since JDK8
 */
@Aspect
@Component
@Order(1)
public class ControllerLogAspect {
    private final Logger logger;
    private final static Map<String, String> FORMAT_MAP = new ConcurrentHashMap<>();

    public ControllerLogAspect() {
        String logName = "unknown";
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
            logName = properties.getProperty("log.name", "unknown");
        } catch (IOException e) {
            System.out.println("log properties error");
        }
        logger = LoggerFactory.getLogger(logName);
    }

    @Around("@annotation(mapping)")
    public Object around(ProceedingJoinPoint point, RequestMapping mapping) throws Throwable {
        StringBuilder builder = new StringBuilder();
        final long cur = System.currentTimeMillis();
        try {
            builder.append("\n\t\tmethod - ").append(point.getTarget().getClass().getName()).append(".")
                    .append(point.getSignature().getName())
                    .append("\n\t\turi - ").append(LocalUtil.getUri())
                    .append("\n\t\tip - ").append(LocalUtil.getIp())
                    .append("\n\t\tbrowser - ").append(LocalUtil.getBrowser())
                    .append("\n\t\tos - ").append(LocalUtil.getOs())
                    .append("\n\t\tsession - ").append(LocalUtil.printUserId())
                    .append("\n\t\trequest - ").append(Format.format(reqFormat(point), point.getArgs()));
            Object result = point.proceed();
            if (result instanceof String) {
                builder.append("\n\t\tresponse - ").append(result);
            } else {
                builder.append("\n\t\tresponse - ").append(JSON.toJSONString(result));
            }
            return result;
        } catch (Throwable e) {
            builder.append("\n\t\terror - ").append(e);
            throw e;
        } finally {
            Long begin = LocalUtil.getBegin();
            begin = begin == null ? cur : begin;
            builder.append("\n\t\ttime - ").append(System.currentTimeMillis() - begin).append("ms");
            logger.info(builder.toString());
        }
    }

    private String format(Annotation[] annotation) {
        if (annotation == null) {
            return " %- ;";
        }
        for (Annotation ann : annotation) {
            if (ann instanceof RequestBody) {
                return " %j ;";
            } else if (ann instanceof RequestParam) {
                return " %s ;";
            } else if (ann instanceof PathVariable) {
                return " %s ;";
            } else if (ann instanceof RequestObject) {
                return " %j ;";
            }
        }
        return " %- ;";
    }

    private String reqFormat(ProceedingJoinPoint point) {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String format;
        format = FORMAT_MAP.get(method.toGenericString());

        if (format != null) {
            return format;
        }

        Annotation[][] annotations = method.getParameterAnnotations();
        format = "";
        for (Annotation[] ann : annotations) {
            format += format(ann);
        }

        FORMAT_MAP.put(method.toGenericString(), format);
        return format;
    }
}