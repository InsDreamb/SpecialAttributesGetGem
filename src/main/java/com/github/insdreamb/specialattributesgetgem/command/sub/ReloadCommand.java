package com.github.insdreamb.specialattributesgetgem.command.sub;

import com.github.insdreamb.specialattributesgetgem.Main;
import com.github.insdreamb.specialattributesgetgem.command.AbstractCommand;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends AbstractCommand {

    public ReloadCommand() {
        super("reload","","重载配置文件", "sag.reload",true);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        try {
            Main.getInstance().reload();
            commandSender.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.reload_process")));
            commandSender.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.reload_success")));
            return true;
        } catch (Exception e) {
            commandSender.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.reload_fault")));
            return false;
        }
    }

}
