package com.quizgame.rest.dto;

public class RestApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public RestApiResponse() {
    }

    public RestApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RestApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> RestApiResponse<T> success(String message) {
        return new RestApiResponse<>(true, message);
    }

    public static <T> RestApiResponse<T> success(String message, T data) {
        return new RestApiResponse<>(true, message, data);
    }

    public static <T> RestApiResponse<T> error(String message) {
        return new RestApiResponse<>(false, message);
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
} 