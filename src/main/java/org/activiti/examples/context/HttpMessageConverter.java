package org.activiti.examples.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @author wangkai
 * @since JDK8
 */
public class HttpMessageConverter extends FastJsonHttpMessageConverter4 {
    private final static ThreadLocal<byte[]> BYTES_LOCAL = new ThreadLocal<>();

    private static byte[] allocateBytes(int length) {
        byte[] chars = BYTES_LOCAL.get();

        if (chars == null) {
            if (length <= 1024 * 64) {
                chars = new byte[1024 * 64];
                BYTES_LOCAL.set(chars);
            } else {
                chars = new byte[length];
            }
        } else if (chars.length < length) {
            chars = new byte[length];
        }
        return chars;
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (obj instanceof String) {
            HttpHeaders headers = outputMessage.getHeaders();
            byte[] body = ((String) obj).getBytes();
            headers.setContentLength(body.length);
            headers.set("ret", "0");
            outputMessage.getBody().write(body);
            return;
        }

        outputMessage.getHeaders().set("ret", "0");
        super.writeInternal(new GlobalResult(obj), outputMessage);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        InputStream in = inputMessage.getBody();

        byte[] bytes = allocateBytes(1024 * 64);
        int offset = 0;

        for (; ; ) {
            int readCount = in.read(bytes, offset, bytes.length - offset);
            if (readCount == -1) {
                break;
            }
            offset += readCount;
            if (offset == bytes.length) {
                byte[] newBytes = new byte[bytes.length * 3 / 2];
                System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
                bytes = newBytes;
            }
        }

        Object object = JSON.parseObject(bytes, 0, offset, super.getFastJsonConfig().getCharset(), type,
                super.getFastJsonConfig().getFeatures());
        LocalUtil.setBody(object);
        return object;
    }

}
