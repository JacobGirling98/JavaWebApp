package com.develogical;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryProcessor {

    public String process(String query) {

//        which of the following numbers is the largest: 2, 55, 520, 814
//        what is 2 plus 15

        System.out.println(query);

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if(query.toLowerCase().contains("your name")) {
            return "CrypticFortress";
        } else if (query.toLowerCase().contains("which of the following numbers is the largest")) {
            return findLargestNumber(query);
        } else if (query.toLowerCase().contains("plus")) {
            return addTwoNumbersFrom(query);
        } else if (query.toLowerCase().contains("multiplied")) {
            return multiplyTwoNumbersFrom(query);
        } else if (query.toLowerCase().contains("square and a cube")) {
            return findSquaredAndCubedNumbers(query);
        } else if (query.toLowerCase().contains("numbers are primes")) {
            List<String> split = splitByColon(query);
            List<String> statement = Arrays.asList(split.get(2).split(","));
            return statement.stream()
                    .map(num -> Integer.parseInt(num.trim()))
                    .filter(num -> isPrime(num))
                    .map(num -> num.toString())
                    .collect(Collectors.joining(","));
        }
        return "";
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
