package com.elite.etl.model;

/**
 * @Author: Wesker
 * @Date: 2019-05-23 9:51
 * @Version: 1.0
 */
public class Result {
    private int error_code;
    private String reason;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Result{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
