package me.jershdervis.monitorj.command;


public class CommandSyntaxException extends Exception {
    /**
     * @param message The error message
     */
    public CommandSyntaxException(String message) {
        super(message);
    }
}
