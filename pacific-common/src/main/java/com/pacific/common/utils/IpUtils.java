package com.pacific.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by Fe on 16/4/12.
 */
public class IpUtils {

    public static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    public static String getRealIp() {
        String localIp = null;// 本地IP，如果没有配置外网IP则返回它
        String netIp = null;// 外网IP
        String returnIp = "";

        try {
            Enumeration<NetworkInterface> netInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                        netIp = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localIp = ip.getHostAddress();
                    }
                }
            }
            if (netIp != null && !"".equals(netIp)) {
                returnIp = netIp;
            } else {
                returnIp = localIp;
            }
        } catch (Exception e) {
            logger.error("getRealIp error ,e : {}",e);
        }
        return returnIp;
    }
}
