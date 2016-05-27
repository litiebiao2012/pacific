package com.pacific.common.web.springmvc.convert;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Fe on 15/6/25.
 */
public class DateConverter implements Converter<String, Date> {

    private static final Logger log = LoggerFactory.getLogger(DateConverter.class);


    @Override
    public Date convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormatSimple = new SimpleDateFormat("yyyy-MM-dd");

        if(StringUtils.isEmpty(source)){
            return null;
        }

        Date result = null;
        dateFormat.setLenient(false);
        try {
            result = dateFormat.parse(source);
        } catch (ParseException e) {

        }
        if(result == null){
            try {
                result = dateFormatSimple.parse(source);
            } catch (ParseException e) {

            }
        }
        if(result == null){
            log.error("日期转换错误！");
        }
        return result;
    }
}
