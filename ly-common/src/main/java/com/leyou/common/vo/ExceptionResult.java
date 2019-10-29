package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * 异常信息返回类
 */
@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;//时间戳

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
