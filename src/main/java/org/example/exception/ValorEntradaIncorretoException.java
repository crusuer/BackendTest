package org.example.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValorEntradaIncorretoException extends RuntimeException {
    public ValorEntradaIncorretoException(String message, Throwable cause) {
        super(message, cause);
        log.debug(getMessage(), getCause());
    }
}
