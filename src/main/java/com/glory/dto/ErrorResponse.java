package com.glory.dto;

// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter

public class ErrorResponse {

    private String status;
    private String message;
    private long timestamp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
