package com.sejin.i18npropertiestranslator.translator.papago.nmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Papago didn\'t give the response expected")
public class PapagoMalfunctioningException extends RuntimeException{
}
