package com.github.insdreamb.specialattributesgetgem.command;

import com.github.insdreamb.specialattributesgetgem.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MainCommand implements CommandExecutor {
    public Map<String, AbstractCommand> commandSubMap = new HashMap<>();

    public void registers(AbstractCommand... abstractCommands){
        for (AbstractCommand abstractCommand : abstractCommands){
            commandSubMap.put(abstractCommand.getSub(), abstractCommand);
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0){
            return help(commandSender,command);
        }
        AbstractCommand abstractCommand = commandSubMap.get(args[0].toLowerCase());
        if ( abstractCommand != null ){
            if ( !abstractCommand.allowConsole() ) {
                if (commandSender instanceof Player) {
                    if ( commandSender.hasPermission(abstractCommand.getPermission()) )
                        return abstractCommand.execute(commandSender, args);
                    else {
                        commandSender.sendMessage(Main.getInstance().config.getString("message.permission"));
                        return false;
                    }
                }
                else {
                    commandSender.sendMessage(Main.getInstance().config.getString("message.console"));
                    return false;
                }
            }
            else {
                if ( commandSender.hasPermission(abstractCommand.getPermission()) )
                    return abstractCommand.execute(commandSender, args);
                else {
                    commandSender.sendMessage(Main.getInstance().config.getString("message.permission"));
                    return false;
                }
            }
        }
        else {
            return help(commandSender, command);
        }
    }

    public boolean help(CommandSender commandSender,Command command){
        for (AbstractCommand value:commandSubMap.values()){
            if (commandSender.hasPermission(value.getPermission())) {
                commandSender.sendMessage(
                        Main.getInstance().config.getString("message.command")
                                .replace("<command>",command.getName())
                                .replace("<command_sub>",value.getSub())
                                .replace("<command_args>",value.getArgs())
                                .replace("<command_description>",value.getDescription())
                                .replace("&","§")
                );
            }
        }
        return false;
    }
}
