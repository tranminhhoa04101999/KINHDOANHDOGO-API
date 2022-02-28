package com.example.kddgmn.payload;

public class CommonResponse {
    private int idResult;
    private String message;

    public CommonResponse(int idResult, String message) {
        this.idResult = idResult;
        this.message = message;
    }

    public CommonResponse() {
    }

    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
