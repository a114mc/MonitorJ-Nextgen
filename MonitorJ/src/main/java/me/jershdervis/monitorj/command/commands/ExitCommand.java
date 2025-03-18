package me.jershdervis.monitorj.command.commands;

import me.jershdervis.monitorj.command.AbstractCommand;

public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "Exit Process.");
    }

    @Override
    public void onCommand(String[] args) {
        System.exit(0);
    }
}
