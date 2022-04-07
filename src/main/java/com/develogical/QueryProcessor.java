package com.develogical;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryProcessor {

    public String process(String query) {

//        which of the following numbers is the largest: 2, 55, 520, 814

        System.out.println(query);

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if(query.toLowerCase().contains("your name")) {
            return "CrypticFortress";
        } else if (query.toLowerCase().contains("which of the following numbers is the largest")) {
            return findLargestNumber(query);
        }
        return "";
    }

    private String findLargestNumber(String query) {
        List<String> split = Arrays.asList(query.toLowerCase().split(":"));
        List<Integer> numbers = Arrays.stream(split.get(1).split(","))
                .map(num -> Integer.parseInt(num.trim()))
                .sorted()
                .collect(Collectors.toList());
        return numbers.get(numbers.size() - 1 ).toString();
    }
}
