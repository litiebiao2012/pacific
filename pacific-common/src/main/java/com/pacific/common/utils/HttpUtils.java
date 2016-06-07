package com.pacific.common.utils;

import com.pacific.common.json.FastJson;
import com.pacific.common.web.RequestContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Fe on 14-9-12.
 */
public class HttpUtils {

    public static boolean isXhr(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (StringUtils.isEmpty(requestType))
            return false;
        else
            return true;
    }


    public static String getIP4(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String requestMessage(HttpServletRequest request) {
        return "seqId : " + RequestContext.getSeq() + ",request uri : "+  request.getRequestURI() +", request body : " + getRequestParam(request);
    }

    public static String responseMessage(Object responseObj) {
        return "seqId : " + RequestContext.getSeq() + ", " + FastJson.toJson(responseObj);
    }

    private static String getRequestParam(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getParameterNames();
        StringBuffer sb = new StringBuffer();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            sb.append(key).append("=").append(value).append("&");
        }

        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
