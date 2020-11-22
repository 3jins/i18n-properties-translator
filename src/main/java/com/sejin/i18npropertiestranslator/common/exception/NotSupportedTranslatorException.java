package com.sejin.i18npropertiestranslator.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The given translator is not supported in this system.")
public class NotSupportedTranslatorException extends RuntimeException {
}
