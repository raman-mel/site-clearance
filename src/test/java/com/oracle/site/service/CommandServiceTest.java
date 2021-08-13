package com.oracle.site.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.oracle.site.command.Command;
import com.oracle.site.command.CommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandServiceTest {

    @Mock
    private CommandMapper commandMapper;

    @Mock
    private Command command;

    private CommandService commandService;

    @BeforeEach
    public void init() {
        commandService = new CommandServiceImpl(commandMapper);
        when(commandMapper.apply(anyString())).thenReturn(command);
    }

    @Test
    public void testGetCommand() {
        final String someCommand = "go";
        assertEquals(command, commandService.getCommand(someCommand));
        verify(commandMapper).apply(someCommand);
    }
}