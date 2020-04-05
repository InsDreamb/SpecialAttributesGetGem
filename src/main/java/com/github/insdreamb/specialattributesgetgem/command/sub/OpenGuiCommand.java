package com.github.insdreamb.specialattributesgetgem.command.sub;

import com.github.insdreamb.specialattributesgetgem.Main;
import com.github.insdreamb.specialattributesgetgem.command.AbstractCommand;
import com.github.insdreamb.specialattributesgetgem.utils.GetGem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenGuiCommand extends AbstractCommand {

    public OpenGuiCommand() {
        super("open","","打开取宝石界面","sag.open",false);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        GetGem.sendInventory((Player)commandSender);
        commandSender.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.open_gui")));
        return false;
    }
}
