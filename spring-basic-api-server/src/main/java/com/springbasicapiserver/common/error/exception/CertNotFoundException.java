package com.springbasicapiserver.common.error.exception;

import com.springbasicapiserver.common.error.ErrorCode;

public class CertNotFoundException extends NotFoundException {
    public CertNotFoundException() {
        super(ErrorCode.CERT_NOT_FOUND);
    }
}
