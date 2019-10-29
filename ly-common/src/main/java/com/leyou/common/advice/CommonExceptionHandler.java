package com.leyou.common.advice;

import com.leyou.common.excption.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理，设置一个拦截器，用于捕获异常
 * 使用springMVC，编写一个通知
 */
@ControllerAdvice//拦截器，默认情况下拦截所有controller
public class CommonExceptionHandler {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handlerException(LyException e){
//        ResponseEntity.status(状态码).body(异常返回结果);
        return ResponseEntity
                .status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }

}
