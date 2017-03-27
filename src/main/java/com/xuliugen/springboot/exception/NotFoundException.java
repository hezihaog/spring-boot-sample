package com.xuliugen.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ResponseStatus 注解可以将异常映射为HTTP状态码
 * Created by xuliugen on 2017/3/27.
 */
@ResponseStatus(value = HttpStatus.METHOD_FAILURE,
        reason = "resource not found!")
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5098261678688444370L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
