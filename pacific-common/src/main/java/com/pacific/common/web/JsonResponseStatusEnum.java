package com.pacific.common.web;

/**
 * ajax 返回时的状态
 *
 * Created by panwang.chengpw on 12/27/14.
 */
public enum JsonResponseStatusEnum {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 系统错误
     */
    FAIL(500),
    /**
     * 未登录
     */
    PARAM_ERR(600),
    /**
     * 未登录
     */
    NO_LOGIN(700);

    private int code;

    private JsonResponseStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
