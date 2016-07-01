package com.pacific.common.helper;

import com.pacific.common.http.HttpClientUtil4;
import com.pacific.common.json.FastJson;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by Fe on 16/6/12.
 */
public class BearyChatHelper {

    private String url;
    private String notification;
    private String attTitle;
    private String attText;
    private String attColor;
    private String attUrl;


    public void sendMessage(String email,String applicationName,String message) {
        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-type","application/json");

        BearyChatRequest bearyChatRequest = new BearyChatRequest();
        bearyChatRequest.setText(message);
        bearyChatRequest.setUser(email);
        bearyChatRequest.setMarkdown(true);
        if (StringUtils.isNotEmpty(applicationName)) {
            this.notification = applicationName + "出现异常,请及时处理!";
        }

        bearyChatRequest.setNotification(notification);
        List<Map<String,Object>> mapList = new LinkedList<Map<String,Object>>();
        Map<String,Object> attMap = new HashMap<String,Object>();
        attMap.put("title",attTitle);
        attMap.put("text",attText);
        attMap.put("color",attColor);
        Map<String,String> urlMap = new HashMap<String,String>();
        urlMap.put("url",attUrl);
        attMap.put("images", Arrays.asList(urlMap));
        mapList.add(attMap);
        bearyChatRequest.setAttachments(mapList);

        HttpClientUtil4.postJson(url,headerMap, FastJson.toJson(bearyChatRequest));

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    class BearyChatRequest {
        private String text;
        private boolean markdown;
        private String channel;
        private String notification;
        private String user;
        private List<Map<String,Object>> attachments;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isMarkdown() {
            return markdown;
        }

        public void setMarkdown(boolean markdown) {
            this.markdown = markdown;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getNotification() {
            return notification;
        }

        public void setNotification(String notification) {
            this.notification = notification;
        }

        public List<Map<String, Object>> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<Map<String, Object>> attachments) {
            this.attachments = attachments;
        }
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getAttTitle() {
        return attTitle;
    }

    public void setAttTitle(String attTitle) {
        this.attTitle = attTitle;
    }

    public String getAttText() {
        return attText;
    }

    public void setAttText(String attText) {
        this.attText = attText;
    }

    public String getAttColor() {
        return attColor;
    }

    public void setAttColor(String attColor) {
        this.attColor = attColor;
    }

    public String getAttUrl() {
        return attUrl;
    }

    public void setAttUrl(String attUrl) {
        this.attUrl = attUrl;
    }
}

