package com.springbasicapiserver.common.error.exception;

import com.springbasicapiserver.common.error.ErrorCode;

public class NotFoundException extends BusinessBaseException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }
}
