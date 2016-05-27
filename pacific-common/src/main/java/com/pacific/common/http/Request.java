package com.pacific.common.http;

import com.pacific.common.web.RequestContext;

/**
 * Created by Fe on 16/3/28.
 */
public class Request {

    private String seqId = RequestContext.getSeq();
    private String body;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
