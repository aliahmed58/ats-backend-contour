package com.training.ats.exceptions;

import java.time.LocalDateTime;

/**
 * A record that will contain the error details to be sent to the frontend
 * @param path url path that caused the error
 * @param message exception message
 * @param statusCode http status code
 * @param localDateTimes time when exception code occurred
 */
public record ServerError(
    String path,
    String message,
    int statusCode,
    LocalDateTime localDateTimes
) {}
