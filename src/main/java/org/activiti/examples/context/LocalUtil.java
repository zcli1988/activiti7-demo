package org.activiti.examples.context;

/**
 * 用于存储一次Http请求中所包含的相关内容
 *
 * @author wangkai
 * @since JDK8
 */
public class LocalUtil {
    private final static ThreadLocal<Local> THREAD_LOCAL = new ThreadLocal<>();

    public static void setBegin(long begin) {
        getLocal().begin = begin;
    }

    public static Long getBegin() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.begin;
    }

    public static void setLoginUser(LoginUser loginUser) {
        getLocal().loginUser = loginUser;
    }

    public static LoginUser getLoginUser() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.loginUser;
    }

    public static void setSession(String session) {
        getLocal().session = session;
    }

    public static String getSession() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.session;
    }

    public static void setUri(String uri) {
        getLocal().uri = uri;
    }

    public static String getUri() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.uri;
    }

    public static void setIp(String ip) {
        getLocal().ip = ip;
    }

    public static String getIp() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.ip;
    }

    public static void setBrowser(String browser) {
        getLocal().browser = browser;
    }

    public static String getBrowser() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.browser;
    }

    public static void setOs(String os) {
        getLocal().os = os;
    }

    public static String getOs() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.os;
    }

    public static void setBody(Object body) {
        getLocal().body = body;
    }

    public static Object getBody() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return null;
        }
        return local.body;
    }

    private static Local getLocal() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            local = new Local();
            THREAD_LOCAL.set(local);
        }
        return local;
    }

    public static String printUserId() {
        Local local = THREAD_LOCAL.get();
        if (local == null) {
            return "";
        }
        String userId = "";
        LoginUser loginUser = local.loginUser;
        if (loginUser != null) {
            userId = loginUser.getId() + "";
        }
        return "userId[" + userId + "],session[" + local.session + "]";
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    static private class Local {
        Long begin;
        LoginUser loginUser;
        String session;
        String uri;
        String ip;
        String browser;
        String os;
        Object body;
    }

}
