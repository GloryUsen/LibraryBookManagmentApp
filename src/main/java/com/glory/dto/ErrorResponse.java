package com.glory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ErrorResponse {

    private String status;
    private String message;
    private long timestamp;

}
