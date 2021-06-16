package com.hospital.Hospital.util;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Utility class to validate HttpServletRequest #getParameter
 *
 */
public class Validation {
    public static int getIntField(HttpServletRequest request, String fieldName) throws IllegalArgumentException{
        String value = request.getParameter(fieldName);
        int result;
        if(value == null) {
            throw new IllegalArgumentException();
        }
        try {
          result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
