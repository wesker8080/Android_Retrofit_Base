package com.elite.etl.net;

/**
 * @Author: Wesker
 * @Date: 2019-05-22 10:32
 * @Version: 1.0
 */
public class Response<T> {
    private int error_code;
    private T result;
    private String reason;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
