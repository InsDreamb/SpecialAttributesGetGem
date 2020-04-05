package com.github.insdreamb.specialattributesgetgem;

import com.github.insdreamb.specialattributesgetgem.command.MainCommand;
import com.github.insdreamb.specialattributesgetgem.command.sub.OpenGuiCommand;
import com.github.insdreamb.specialattributesgetgem.command.sub.ReloadCommand;
import com.github.insdreamb.specialattributesgetgem.listener.Listener;
import com.github.insdreamb.specialattributesgetgem.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
    private MainCommand mainCommand;
    private static Main instance;
    private static Lore lore;
    private static Variable variable;
    private GetGem getGem;
    public FileConfiguration config;
    public FileConfiguration body;
    public final String DATAFOLDER = "plugins"+File.separator+"SpecialAttributesGetGem";

    public String replaceChar(String str){
        return str.replaceAll("&","§");
    }

    @Override
    public void onEnable() {
        instance = this;
        lore = new Lore();
        variable = new Variable();
        getGem = new GetGem();
        this.mainCommand = new MainCommand();
        mainCommand.registers(
                new ReloadCommand(),
                new OpenGuiCommand()
        );
        getCommand("sag").setExecutor(mainCommand);
        load();
    }

    public FileConfiguration loadYAML(File file){
        try{
            if ( !file.exists() ) this.saveResource("config.yml",true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public void load(){
        config = loadYAML(new File(DATAFOLDER,"config.yml"));
        try {
            body = loadYAML(new File("plugins" + File.separator + "SpecialAttributes", "宝石.yml"));
        }
        catch (Exception e){
            e.printStackTrace();
            this.getServer().getLogger().info("[拆宝石]获取本体插件的宝石配置文件失败");
        }
        if (EconomyUtil.setupEconomy()) {
            ReadName.read();
            this.getServer().getPluginManager().registerEvents(new Listener(),this);
        }
        else {
            System.out.println("[取宝石]未找到经济插件 已关闭插件");
            this.setEnabled(false);
            return;
        }
    }

    public void unload(){
        Variable.equips.clear();
        InventoryClickEvent.getHandlerList().unregister(this);
    }

    public void reload(){
        unload();
        load();
    }

    public static Main getInstance() { return instance; }

    public Lore getLore(){
        return lore;
    }

    public Variable getVariable() {
        return variable;
    }

    public GetGem getGetGem() {
        return getGem;
    }

}
