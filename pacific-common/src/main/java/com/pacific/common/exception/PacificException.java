package com.pacific.common.exception;

import com.pacific.common.web.result.AjaxResult;

import java.io.Serializable;

/**
 * Created by Fe on 16/5/27.
 */
public class PacificException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -416273111872183365L;
    private String code;
    private Throwable t;

    public PacificException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public PacificException(String code, String msg, Throwable t) {
        super(msg,t);
        this.code = code;
        this.t = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

    public static void throwEx(String status, String message) {
        PacificException pacificException = new PacificException(status,message);
        throw pacificException;
    }

    public static void throwEx( String message) {
        PacificException pacificException = new PacificException(AjaxResult.STATUS_ERROR,message);
        throw pacificException;
    }
}
