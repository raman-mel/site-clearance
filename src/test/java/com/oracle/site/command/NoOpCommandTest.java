package com.oracle.site.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoOpCommandTest {

    @Test
    public void testToString() {
        assertEquals(new NoOpCommand().toString(), " (invalid)");
    }

    @Test
    public void testToString2() {
        NoOpCommand command = new NoOpCommand();
        command.setCommandString("hello");
        assertEquals(command.toString(), "hello (invalid)");
    }
}