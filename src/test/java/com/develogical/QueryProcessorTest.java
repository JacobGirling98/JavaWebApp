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
}
