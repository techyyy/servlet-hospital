package com.hospital.Hospital.util;

import java.util.ArrayList;
import java.util.List;

import static com.hospital.Hospital.web.constants.NumberConstants.NUMBER_OF_RECORDS_PER_PAGE;


/**
 *
 * Pagination utility class
 *
 */
public class Pagination {

    public static<T> int numberOfPages(List<T> list){
        int size = list.size();
        return size/NUMBER_OF_RECORDS_PER_PAGE + (size % NUMBER_OF_RECORDS_PER_PAGE == 0 ? 0 : 1);
    }

    public static<T> List<T> sublist(List<T> list, int start, int end) {
        List<T> result = new ArrayList<>();
        for(int i = start; i < end; i++) {
            if(i < list.size()) {
                result.add(list.get(i));
            }
        }
        return result;
    }
}
