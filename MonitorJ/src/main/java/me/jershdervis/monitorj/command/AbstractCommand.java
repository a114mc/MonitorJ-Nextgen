package me.jershdervis.monitorj.command;

public abstract class AbstractCommand {
    public static String name;
    public static String description;

    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }
    /**
     * @param args
     * <p>[0] the base command</p>
     * <p>[1] OPTIONAL</p>
     * <p>[2] OPTIONAL</p>
     * <p>ETC.</p>
     **/
    public abstract void onCommand(String[] args) throws CommandSyntaxException;
}
