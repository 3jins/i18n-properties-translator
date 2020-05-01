package com.sejin.i18npropertiestranslator.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@Getter
public class PropertiesDataFormatException extends RuntimeException {
    private final String defaultMessage = "Data format of properties is inappropriate.";
    private final HttpStatus errorCode = HttpStatus.BAD_REQUEST;
    private final String message;
    private final String[] params;

    public PropertiesDataFormatException(final String message, final String... params) {
        this.message = StringUtils.isEmpty(message) ? defaultMessage : message;
        this.params = params == null ? new String[]{} : params;
    }
}
