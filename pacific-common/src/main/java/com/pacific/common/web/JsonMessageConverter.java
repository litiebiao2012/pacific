package com.pacific.common.web;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.pacific.common.http.HttpUtils;
import com.pacific.common.json.FastJson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

/**
 * 返回json时使用, 支持json , jsonp, 需配合RequestContext 使用, 注意
 *
 * Created by panwang.chengpw on 5/8/15.
 */
public class JsonMessageConverter extends FastJsonHttpMessageConverter {

    private static final Logger logger              = LoggerFactory.getLogger(JsonMessageConverter.class);
    private static       String      CALLBACK_REGEXP     = "[^0-9a-zA-Z_\\.]";
    private static       int         CALLBACK_MAX_LENGTH = 128;
    private static       Pattern     PATTERN             = Pattern.compile(CALLBACK_REGEXP);
    private static       String      JSON_HEADER_APPEND  = "\r\n\r\n";
    private static       String      CHAR_SET            = "UTF-8";
    private static       ValueFilter filter              = new JsonStringValueFilter();

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        String text = JsonCommonRender.getJsonResult(obj);
        byte[] bytes = text.getBytes(getCharset());
        out.write(bytes);
        logger.debug(HttpUtils.responseMessage(obj));
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        //读取文件流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = inputMessage.getBody();
        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }
            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }
        byte[] bytes = baos.toByteArray();

        String requestBody = new String(bytes, "utf-8");
        logger.info("请求seq{},内容:{}",RequestContext.getSeq(),requestBody);
        if(StringUtils.isEmpty(requestBody)){
            return null;
        }
        Object requestObj = FastJson.fromJson(requestBody, clazz);
        return requestObj;
    }

}
