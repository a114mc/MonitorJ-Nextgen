
package me.jershdervis.monitorj.command.commands;


import cn.a114.utils.ServerPrinter;
import me.jershdervis.monitorj.MonitorJ;
import me.jershdervis.monitorj.command.AbstractCommand;
import me.jershdervis.monitorj.command.CommandSyntaxException;
import me.jershdervis.monitorj.server.ServerManager;
import me.jershdervis.monitorj.util.ScannerUtils;

import java.io.IOException;

/**
 * @author a114mc
 */
public class SocketsCommand extends AbstractCommand {
    //TODO: 更好的算命

    static final int length = 3;

    public SocketsCommand() {
        super("sockets", "socket manager");
    }

    @Override
    public void onCommand(String[] args) throws CommandSyntaxException {

        // ACTION
        String action = null;
        try {
            action = args[1].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        if(args.length<=length){
            // 算命
            _566X5ZG9:
            ServerPrinter.println("命令实参过短，正在给您算命。");
            ServerPrinter.println("输入add或remove来添加或删除socket.");
            action = ScannerUtils.nextLine();

        }


        // -VALIDATE ACTION
        if(!args[1].matches("add|remove")){
            throw new CommandSyntaxException("Action Must Be \"add\" or \"remove\"");
        }
        // VALIDATE ACTION-

        //NAME
        String name = args[2];//NAME

        //PORT
        String port = args[3];//PORT



        {
            if (port.matches("[^0-9]")) {
                throw new CommandSyntaxException("Second argument can only contains 0-9");
            }
            //
            //if (!(Integer.valueOf(args[3]) <= 65535 && Integer.valueOf(args[3]) >= 0)) {
            //    throw new RuntimeException("INVALID PORT");
            //}
        }

        // DESCRIPTION
        String desc = args[4];

        try {
            System.out.println("Listening on port: " + port);
            ServerManager.instance.listenOnPort(Integer.valueOf(port));

            MonitorJ.getInstance().getFileManager().saveSocketValue(name, Integer.valueOf(port), desc);

        } catch (IOException e) {
            synchronized (this){
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
    }
}
