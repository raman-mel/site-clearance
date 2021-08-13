package com.oracle.site.service;

import com.oracle.site.command.Command;
import com.oracle.site.command.CommandMapper;

public class CommandServiceImpl implements CommandService {

    private final CommandMapper commandMapper;

    public CommandServiceImpl(final CommandMapper commandMapper) {
        this.commandMapper = commandMapper;
    }

    @Override
    public Command getCommand(final String commandString) {
        return commandMapper.apply(commandString);
    }
}
