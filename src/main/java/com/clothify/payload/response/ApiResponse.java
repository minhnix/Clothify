package com.clothify.payload.response;

public class ApiResponse {
    private String message;
    private int code;
    private Object metadata;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ApiResponse(Object data, Object metadata, String message, int code) {
        this.message = message;
        this.code = code;
        this.metadata = metadata;
        this.data = data;
    }

    public ApiResponse(Object data, String message, int code) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ApiResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static ApiResponse success(Object data, Object metadata, String message) {
        return new ApiResponse(data, metadata, message, 200);
    }

    public static ApiResponse successWithoutMeta(Object data, String message) {
        return new ApiResponse(data, message, 200);
    }

    public static ApiResponse successWithoutDataAndMeta(String message) {
        return new ApiResponse(message, 200);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
