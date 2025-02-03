package com.intlfoodsource.IFSHardware.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private Integer status; // HTTP Status code
    private String message; // Error message
    private String path; // Error request path
    private Long timestamp;

    public ErrorResponse(Integer status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
    }
}
