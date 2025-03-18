package me.jershdervis.monitorj.command;

import me.jershdervis.monitorj.command.commands.ExitCommand;
import me.jershdervis.monitorj.command.commands.SocketsCommand;
import me.jershdervis.monitorj.util.ScannerUtils;

public class CommandManager {

    public static SocketsCommand socketsCommand;
    public static ExitCommand exitCommand;

    public CommandManager() {
        socketsCommand = new SocketsCommand();
        exitCommand = new ExitCommand();

    }

    public void start() {
        String[] command;
        while (true) {
            try {
                command = ScannerUtils.nextLine().split(" ");
                switch (command[0].toLowerCase()) {
                    case "sm":
                    case "socket":
                    case "sockets":
                        socketsCommand.onCommand(command);
                        break;
                    case "exit":
                        exitCommand.onCommand(command);
                        break;
                    default:
                        System.out.print("Command Not Found.\n" + "Valid Commands:\n" + "sm\n" + "socket\n" + "sockets\n" + "exit\n");
                        break;
                }
            } catch (CommandSyntaxException e) {
                synchronized (this) {
                    e.printStackTrace();
                }
            }
        }
    }
}
