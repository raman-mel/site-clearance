package com.oracle.site.command;

import com.oracle.site.model.Site;

public class NoOpCommand implements Command {

    private String commandString;

    public String getCommandString() {
        return commandString;
    }

    public void setCommandString(final String commandString) {
        this.commandString = commandString;
    }

    @Override
    public void execute(final Site site) {
        System.out.println("Invalid command");
    }

    @Override
    public String toString() {
        return (commandString == null ? "" : commandString) + " (invalid)";
    }
}
