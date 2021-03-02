package com.crawler.backend.utils;

import java.util.Date;

public class Response {

    //返回数据封装
    private int code;
    private String message;
    private Object data;
    private Date time;

    public Response(Object data) {
        this.code = 200;
        this.message = "请求成功！";
        this.data = data;
        this.time = new Date();
    }

    public Response(int code, Object data) {
        this.code = code;
        this.message = "";
        this.data = data;
        this.time = new Date();
    }

    public Response(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
        this.time = new Date();
    }

    public Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = new Date();
    }

    @Override
    public String toString() {
        return "Response{" +
                "\ncode=" + code +
                "\n, message='" + message + '\'' +
                "\n, data=" + data +
                "\n, time=" + time +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
