package com.example.sabaynews.models;

import com.google.gson.annotations.SerializedName;

public class BaseResponse <T> {
    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private T data;

    @SerializedName("message")
    private String message;

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
