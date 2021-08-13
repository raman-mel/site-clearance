package com.oracle.site.service;

import com.oracle.site.command.Command;

public interface CommandService {

    Command getCommand(String commandString);

}
