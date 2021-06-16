package com.hospital.Hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Utility class to validate HttpServletRequest #getParameter
 *
 */
public class Validation {

    private static final Logger LOG = LogManager.getLogger(Validation.class);

    public static int getIntField(HttpServletRequest request, String fieldName) throws IllegalArgumentException{
        String value = request.getParameter(fieldName);
        int result;
        if(value == null) {
            throw new IllegalArgumentException();
        }
        try {
          result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOG.error(e.getMessage());
            throw new IllegalArgumentException();
        }
        return result;
    }
}
