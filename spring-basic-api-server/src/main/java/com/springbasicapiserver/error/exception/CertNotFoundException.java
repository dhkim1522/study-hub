package com.springbasicapiserver.error.exception;

import com.springbasicapiserver.error.ErrorCode;

public class CertNotFoundException extends NotFoundException {
    public CertNotFoundException() {
        super(ErrorCode.CERT_NOT_FOUND);
    }
}
