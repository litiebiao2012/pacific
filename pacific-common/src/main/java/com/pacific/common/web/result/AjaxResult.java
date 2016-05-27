package com.pacific.common.web.result;

/**
 * Created by Fe on 16/5/27.
 */
public class AjaxResult {
    public static final String STATUS_OK       = "OK";
    public static final String STATUS_ERROR    = "ERROR";
    public static final String STATUS_NO_LOGIN = "NO_LOGIN";
    public static final String STATUS_PARAM_ERROR = "PARAM_ERROR";
    public static final String NO_PERMISSION = "NO_PERMISSION";

    public AjaxResult() {
        this.status = STATUS_OK;
    }

    public AjaxResult(Object data) {
        this.data = data;
        this.status = STATUS_OK;
    }

    public AjaxResult(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public AjaxResult(String status, String msg, Object data) {
        this.status = status;
        this.message = msg;
        this.data = data;
    }



    /**
     * 返回状态
     */
    private String status=STATUS_OK;
    /**
     * 异常提示
     */
    private String message;
    /**
     * 业务数据
     */
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static AjaxResult newNoLoginResult() {
        return new AjaxResult(STATUS_NO_LOGIN,"未登陆!");
    }

    public static AjaxResult newNoPermissionResult() {
        return new AjaxResult(NO_PERMISSION,"没有权限!");
    }

    public void setErrorMessage(String errorMessage) {
        this.setStatus(AjaxResult.STATUS_ERROR);
        this.message = errorMessage;
    }

}
