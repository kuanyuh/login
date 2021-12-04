package xyz.kuanyu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogUtil {

    public static void logSession(String prefix, HttpSession session, String att){
        System.out.println(prefix + att + ":" + session.getAttribute(att));
    }

    public static void logRequest(String prefix, HttpServletRequest request, String att){
        System.out.println(prefix + att + ":" + request.getParameter(att));
    }

    public static void log(String prefix, String att){
        System.out.println(prefix+":"+att);
    }
}
