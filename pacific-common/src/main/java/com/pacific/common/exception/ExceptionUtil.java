package com.pacific.common.exception;

import java.io.CharArrayWriter;
import java.io.PrintWriter;


/**
 * 为常见的exceptions做公共操作
 * @author zhanghua
 *
 */
public class ExceptionUtil {

    /**
     * 将异常栈组织成String类型并返回
     * @param e
     * @return
     */
    public static String exToString(Throwable e){
        if(e==null){
            return null;
        }
        CharArrayWriter charWriter=new CharArrayWriter(150);
        PrintWriter writer=new PrintWriter(charWriter);
        e.printStackTrace(writer);
        writer.flush();
        return charWriter.toString();
    }

}
