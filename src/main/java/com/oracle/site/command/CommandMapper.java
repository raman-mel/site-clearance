package com.oracle.site.command;

import com.oracle.site.service.SiteService;

import java.util.Locale;
import java.util.function.Function;
import java.util.regex.Pattern;

public class CommandMapper implements Function<String, Command> {

    private final static String ADVANCE_COMMAND_PATTERN = "(a|advance) \\d{1,}$";

    private final Command advanceCommand;
    private final Command leftCommand;
    private final Command rightCommand;
    private final Command quitCommand;
    private final Command noOpCommand;

    public CommandMapper(final SiteService siteService) {
        this.advanceCommand = new AdvanceCommand(siteService);
        this.leftCommand = new LeftCommand(siteService);
        this.rightCommand = new RightCommand(siteService);
        this.quitCommand = new QuitCommand(siteService);
        this.noOpCommand = new NoOpCommand();
    }

    @Override
    public Command apply(final String commandString) {
        final String command = commandString.toLowerCase(Locale.getDefault());
        if (isAdvanceCommand(command)) {
            ((AdvanceCommand) advanceCommand).setSteps(Integer.valueOf(command.split(" ")[1]));
            return advanceCommand;
        } else if (isTurnLeftCommand(command)) {
            return leftCommand;
        } else if (isTurnRightCommand(command)) {
            return rightCommand;
        } else if (isQuitCommand(command)) {
            return quitCommand;
        } else {
            ((NoOpCommand) noOpCommand).setCommandString(commandString);
            return noOpCommand;
        }
    }

    private boolean isAdvanceCommand(final String commandString) {
        return Pattern.matches(ADVANCE_COMMAND_PATTERN, commandString);
    }

    private boolean isTurnLeftCommand(final String commandString) {
        return commandString.equals("l") || commandString.equals("left");
    }

    private boolean isTurnRightCommand(final String commandString) {
        return commandString.equals("r") || commandString.equals("right");
    }

    private boolean isQuitCommand(final String commandString) {
        return commandString.equals("q") || commandString.equals("quit");
    }
}
