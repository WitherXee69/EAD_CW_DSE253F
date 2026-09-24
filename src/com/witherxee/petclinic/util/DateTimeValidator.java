package com.witherxee.petclinic.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateTimeValidator {
    public static boolean isValidAndFuture(String dateTimeStr, String formatPattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeStr, formatter);

            return parsedDateTime.isAfter(LocalDateTime.now());

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}