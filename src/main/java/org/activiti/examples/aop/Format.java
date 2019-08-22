package org.activiti.examples.aop;

import com.alibaba.fastjson.JSON;

/**
 * @author wangkai
 * @since JDK8
 */
class Format {
    public static String format(String format, Object... args) {
        int e, s = 0;
        final StringBuilder builder = new StringBuilder();
        for (Object arg : args) {
            e = format.indexOf("%", s);
            if (e < 0) {
                break;
            }
            builder.append(format.substring(s, e));
            switch (format.charAt(e + 1)) {
                case '%':
                    builder.append("%");
                    break;
                case '-':
                    builder.append("-");
                    break;
                case 's':
                    builder.append(arg);
                    break;
                case 'j':
                    builder.append(JSON.toJSONString(arg));
                    break;
                default:
                    break;
            }
            s = e + 2;
        }
        builder.append(format.substring(s));
        return builder.toString();
    }

}
