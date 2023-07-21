package com.example.chatgpt.demo.utils;

public class CustomResponse {

    // code
    private int code;

    // message
    private String message;

    // data
    private Object data;

    // no-arg constructor
    public CustomResponse() {
    }

    // all-arg constructor
    public CustomResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // getters and setters
    // @return code
    public int getCode() {
        return code;
    }

    // setters for all the fields
    // @param code
    public void setCode(int code) {
        this.code = code;
    }

    // getters and setters
    // @return message
    public String getMessage() {
        return message;
    }

    // setters for all the fields
    // @param message
    public void setMessage(String message) {
        this.message = message;
    }

    // getters and setters
    // @return data
    public Object getData() {
        return data;
    }

    // setters for all the fields
    // @param data
    public void setData(Object data) {
        this.data = data;
    }

    // toString method
    // @return String
    @Override
    public String toString() {
        return "CustomResponse [code=" + code + ", message=" + message + ", data=" + data + "]";
    }

}
