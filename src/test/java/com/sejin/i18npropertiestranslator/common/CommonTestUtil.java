package com.sejin.i18npropertiestranslator.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CommonTestUtil {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper()
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                    .writeValueAsString(obj);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
