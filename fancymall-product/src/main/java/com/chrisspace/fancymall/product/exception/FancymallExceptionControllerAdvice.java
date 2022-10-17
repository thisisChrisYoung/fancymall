package com.chrisspace.fancymall.product.exception;

import com.chrisspace.fancymall.common.exception.BizCodeEnum;
import com.chrisspace.fancymall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理异常
 */
@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "com.chrisspace.fancymall.product.controller")
@RestControllerAdvice(basePackages = "com.chrisspace.fancymall.product.controller")
public class FancymallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验问题{}, 异常类型{}", e.getMessage(), e.getClass());
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        result.getFieldErrors().forEach((item) -> {
            String message = item.getDefaultMessage();
            String field = item.getField();
            map.put(field, message);
        });

        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data", map);
    }

    // 处理任意异常
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){

        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(),BizCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
