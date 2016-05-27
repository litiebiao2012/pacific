package com.pacific.common.http;

/**
 * Created by Fe on 16/3/28.
 */
public class Response {

    private String seqId;
    private Object obj;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
