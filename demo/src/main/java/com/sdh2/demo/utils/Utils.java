package com.sdh2.demo.utils;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    /**
     * function to convert Date to DD/MM/YY format **
     */
    public static String convertDate(String date) {
        return date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
    }


    /**
     * function find duplicate numbers from list
     *
     * @param
     */

    public static Map<Integer, Long> findDuplicateNumbers(List<Integer> numbers) {
        return numbers.stream().filter(number -> Collections.frequency(numbers, number) > 1)
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()));
    }


    /**
     * function find the duplicate numbers from list with occurrence
     *
     * @param
     */

    public static Map<Integer, Long> findDuplicateNumbersWithOccurrence(List<Integer> numbers) {
        return numbers.stream().filter(number -> Collections.frequency(numbers, number) > 1).collect(Collectors.groupingBy(number -> number, Collectors.counting()));
    }


    public static void main(String[] args) {
        System.out.println(convertDate("2021-01-01"));
        System.out.println(convertDate("2021-01-01T00:00:00.000Z"));
        System.out.println(findDuplicateNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 5, 8, 5, 9, 10, 11, 11)));
        System.out.println(findDuplicateNumbersWithOccurrence(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 5, 5, 8, 9, 10, 11, 11)));

    }

}
