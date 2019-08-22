package org.activiti.examples.util;

import java.util.UUID;

/**
 * @author wangkai
 * @since JDK8
 */
public class UUIDUtil {
    private static String Token_Prefix = "token_";

    public static String generateToken() {
        return Token_Prefix + UUID.randomUUID().toString().replace("-", "");
    }
}
