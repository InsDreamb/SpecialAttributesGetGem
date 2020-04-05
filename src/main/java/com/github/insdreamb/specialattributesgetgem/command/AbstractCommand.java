package com.github.insdreamb.specialattributesgetgem.command;

import org.bukkit.command.CommandSender;

public abstract class AbstractCommand {
    private String sub;
    private String args;
    private String description;
    private String permission;
    private boolean console;

    public AbstractCommand(String sub, String args, String description, String permission, boolean console){
        this.sub = sub;
        this.args = args;
        this.description = description;
        this.permission = permission;
        this.console = console;
    }

    public String getSub() {
        return sub;
    }

    public String getArgs() {
        return args;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public boolean allowConsole() {
        return console;
    }

    public abstract boolean execute(CommandSender commandSender, String[] args);

}
