package com.xiaoyi.birthdayreminder.exception;

import com.xiaoyi.birthdayreminder.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseEntity<Result<?>> handleMaxUploadSizeExceeded() {
        String message = "文件大小超过限制";
        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE) // 413状态码
                .body(Result.error( message));
    }
}