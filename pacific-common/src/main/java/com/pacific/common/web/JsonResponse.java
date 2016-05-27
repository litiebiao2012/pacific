package com.pacific.common.web;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pan on 2014/3/22.
 */
public class JsonResponse {

    private JsonResponseStatusEnum status;
    private Object                 data;

    public JsonResponse(JsonResponseStatusEnum resultStatus, Object data) {
        this.status = resultStatus;
        this.data = resultStatus;
    }

    public JsonResponse(JsonResponseStatusEnum status) {
        this.status = status;
    }

    public JsonResponse() {
        this.status = JsonResponseStatusEnum.SUCCESS;
    }

    public JsonResponse(Object data) {
        this.status = JsonResponseStatusEnum.SUCCESS;
        this.data = data;
    }

    public int getStatus() {
        if (status == null) {
            return JsonResponseStatusEnum.SUCCESS.getCode();
        }
        return status.getCode();
    }

    public void setStatus(JsonResponseStatusEnum resultStatus) {
        this.status = resultStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
