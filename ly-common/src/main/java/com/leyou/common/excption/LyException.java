package com.leyou.common.excption;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 编写项目的通用异常类
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LyException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

}
