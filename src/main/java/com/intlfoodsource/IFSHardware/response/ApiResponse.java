package com.intlfoodsource.IFSHardware.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer status; // HTTP Status code
    private String message; // Response message
    private T data; // return data;
    private Long timestamp;

    public ApiResponse(Integer status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(200, "Success", data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(201, "Resource created successfully", data);
    }
}
