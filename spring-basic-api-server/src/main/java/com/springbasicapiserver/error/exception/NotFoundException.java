package com.springbasicapiserver.error.exception;

import com.springbasicapiserver.error.ErrorCode;

public class NotFoundException extends BusinessBaseException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }
}
