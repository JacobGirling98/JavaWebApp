package com.develogical;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryProcessor {

    public String process(String query) {

//        which of the following numbers is the largest: 2, 55, 520, 814
//        what is 2 plus 15

        System.out.println(query);

        String lowerCaseQuery = query.toLowerCase();
        if (lowerCaseQuery.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if(lowerCaseQuery.contains("your name")) {
            return "CrypticFortress";
        } else if (lowerCaseQuery.contains("which of the following numbers is the largest")) {
            return findLargestNumber(query);
        } else if (lowerCaseQuery.contains("plus")) {
            return addTwoNumbersFrom(query);
        } else if (lowerCaseQuery.contains("multiplied")) {
            return multiplyTwoNumbersFrom(query);
        } else if (lowerCaseQuery.contains("square and a cube")) {
            return findSquaredAndCubedNumbers(query);
        } else if (lowerCaseQuery.contains("numbers are primes")) {
            return findPrimes(query);
        } else if (lowerCaseQuery.contains("fibonacci sequence")) {
            List<String> split = splitByColon(query);

            final Pattern pattern = Pattern.compile("what is the ([0-9]+)th number in the Fibonacci sequence", Pattern.CASE_INSENSITIVE);
            // Match regex against input
            String input = split.get(1).trim();
            final Matcher matcher = pattern.matcher(input);
            // Use results...
            boolean test = matcher.matches();

            String index = matcher.group(1);

//            String indexStr = Arrays.stream(split.get(1).split(" "))
//                    .filter(text -> text.contains("th"))
//                    .findFirst()
//                    .get();
//
//
//            int index = Integer.parseInt(indexStr.substring(0, 2));
            return String.valueOf(fibonacci(Integer.parseInt(index) - 1));
        }
        return "";
    }

    private int fibonacci(int index) {
        if (index < 2) {
            return 1;
        }
        return fibonacci(index - 1) + fibonacci(index - 2);
    }

    private String findPrimes(String query) {
        List<String> split = splitByColon(query);
        List<String> statement = Arrays.asList(split.get(2).split(","));
        return statement.stream()
                .map(num -> Integer.parseInt(num.trim()))
                .filter(num -> isPrime(num))
                .map(num -> num.toString())
                .collect(Collectors.joining(","));
    }

    private boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private String findIfSquareAndCube(int number) {
        Boolean firstNumIsSquare = false;
        Boolean firstNumIsCube = false;

        int squareRes = 0;
        while (Math.pow(squareRes, 2) <= number) {

            if (Math.pow(squareRes, 2) == number) {
                firstNumIsSquare = true;
                break;
            }
            squareRes++;
        }
        squareRes = 0;
        while (Math.pow(squareRes, 3) <= number) {

            if (Math.pow(squareRes, 3) == number) {
                firstNumIsCube = true;
                break;
            }
            squareRes++;
        }
        if (firstNumIsSquare && firstNumIsCube) {
            return Integer.toString(number);
        } else {
            return "";
        }
    }

    private String findSquaredAndCubedNumbers(String query) {
        List<String> split = splitByColon(query);
        List<String> statement = Arrays.asList(split.get(2).split(","));

        String values = statement.stream()
                .map(num -> Integer.parseInt(num.trim()))
                .map(num -> findIfSquareAndCube(num))
                .filter(ans -> ans != "")
                .collect(Collectors.joining(","));
        return values;
    }

    private String multiplyTwoNumbersFrom(String query) {
        List<String> split = splitByColon(query);
        List<String> statement = Arrays.asList(split.get(1).split(" "));
        Integer firstNum = getIntByIndex(statement, 3);
        Integer secondNum = getIntByIndex(statement, 6);
        return Integer.toString(firstNum * secondNum);
    }

    private String addTwoNumbersFrom(String query) {
        List<String> split = splitByColon(query);
        List<String> statement = Arrays.asList(split.get(1).split(" "));
        Integer firstNum = getIntByIndex(statement, 3);
        Integer secondNum = getIntByIndex(statement, 5);
        return Integer.toString(firstNum + secondNum);
    }

    private int getIntByIndex(List<String> statement, int i) {
        return Integer.parseInt(statement.get(i).trim());
    }

    private String findLargestNumber(String query) {
        List<String> split = splitByColon(query);
        List<Integer> numbers = Arrays.stream(split.get(2).split(","))
                .map(num -> Integer.parseInt(num.trim()))
                .sorted()
                .collect(Collectors.toList());
        return numbers.get(numbers.size() - 1).toString();
    }

    private List<String> splitByColon(String query) {
        return Arrays.asList(query.toLowerCase().split(":"));
    }
}
