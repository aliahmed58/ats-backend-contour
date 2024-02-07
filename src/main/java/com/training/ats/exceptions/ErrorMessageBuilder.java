package com.training.ats.exceptions;

public class ErrorMessageBuilder {
    public static String getMessage(String logger, ErrorType type) {
        return type.error + " | called from: " + logger;
    }
}
