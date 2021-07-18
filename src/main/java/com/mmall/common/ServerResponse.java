package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponsCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponsCode.SUCCESS.getCode());
    }
    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ResponsCode.SUCCESS.getCode(), msg);
    }
    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponsCode.SUCCESS.getCode(), data);
    }
    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ResponsCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponsCode.ERROR.getCode(), ResponsCode.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponsCode.ERROR.getCode(), errorMessage);
    }
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }
}
