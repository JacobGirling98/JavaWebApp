package com.develogical;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QueryProcessorTest {

    QueryProcessor queryProcessor = new QueryProcessor();

    @Test
    public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
        assertThat(queryProcessor.process("test"), is(""));
    }

    @Test
    public void knowsAboutShakespeare() throws Exception {
        assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
    }

    @Test
    public void isNotCaseSensitive() throws Exception {
        assertThat(queryProcessor.process("shakespeare"), containsString("playwright"));
    }

    @Test
    public void canGetTeamName() {
        assertThat(queryProcessor.process("your name"), containsString("CrypticFortress"));
    }

    @Test
    public void canGetLargestNumber() {
        String query = "asdfsd:which of the following numbers is the largest: 2, 55, 520, 814";
        assertEquals("814", queryProcessor.process(query));
    }

    @Test
    public void canAddTwoNumbers() {
        String query = "asdfsadfds: what is 2 plus 15";
        assertEquals("17", queryProcessor.process(query));
    }

    @Test
    public void canReadLargestNumberWithFullQuery() {
        String query = "af1a6f30: which of the following numbers is the largest: 57, 231, 970, 97";
        assertEquals("970", queryProcessor.process(query));
    }

    @Test
    public void canAddTwoNumbersFullQuery() {
        String query = "79c07220: what is 4 plus 13";
        assertEquals("17", queryProcessor.process(query));
    }

    @Test
    public void canMultiplyTwoNumbers() {
        String query = "457c8be0: what is 19 multiplied by 14";
        assertEquals("266", queryProcessor.process(query));
    }

    @Test
    public void neitherIsSquareOrCube() {
        String query = "9f764750: which of the following numbers is both a square and a cube: 36, 685";
        assertEquals("", queryProcessor.process(query));
    }

    @Test
    public void oneIsSquareOrCube() {
        String query = "9f764750: which of the following numbers is both a square and a cube: 1, 64";
        assertEquals("1,64", queryProcessor.process(query));
    }

    @Test
    public void multipleNumbersSquareOrCube() {
        String query = "62d93000: which of the following numbers is both a square and a cube: 600, 310, 169, 529";
        assertEquals("", queryProcessor.process(query));
    }

    @Test
    public void whatColourIs() {
        String query = "5041ce20: what colour is a banana";
        assertEquals("yellow", queryProcessor.process(query));
    }

    @Test
    public void primeNumbers() {
        String query = "860f9740: which of the following numbers are primes: 439, 618, 53, 449";
        assertEquals("439,53,449",queryProcessor.process(query));
    }

    @Test
    public void finbonacciSequence() {
        String query = "1d0e5e20: what is the 18th number in the Fibonacci sequence";
        assertEquals("2584", queryProcessor.process(query));
    }
}
