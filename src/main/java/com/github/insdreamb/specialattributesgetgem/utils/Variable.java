package com.github.insdreamb.specialattributesgetgem.utils;

import com.github.insdreamb.specialattributesgetgem.GemInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Variable {

    public static Map<String,Double> equips = new HashMap<>();
    public static Map<String, GemInventory> inventoryMap = new HashMap<>();

    public static final int FIRST_ARROW = 13;
    public static final int Second_ARROW = 31;
    public static final int WEAOPN = 19;
    public static final int ANVIL = 22;
    public static final int GEM = 25;

    /** 初始化GUI **/
    public Inventory initInventory(String player){
        Inventory inventory = Bukkit.createInventory(Bukkit.getPlayer(player),45,"§b取宝石界面");
        //黑色玻璃板
        ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE);
        black.setDurability((byte)15);
        //橙色玻璃板
        ItemStack orange = new ItemStack(Material.STAINED_GLASS_PANE);
        orange.setDurability((byte)1);
        ItemMeta itemMeta = orange.getItemMeta();
        itemMeta.setDisplayName("§6此处放入已镶嵌物品");
        orange.setItemMeta(itemMeta);
        itemMeta = null;
        //蓝色玻璃板
        ItemStack blue = new ItemStack(Material.STAINED_GLASS_PANE);
        blue.setDurability((byte)11);
        //绿色玻璃板
        ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE);
        green.setDurability((byte)5);
        itemMeta = green.getItemMeta();
        itemMeta.setDisplayName("§a此处为宝石出口");
        green.setItemMeta(itemMeta);
        itemMeta = null;
        //切换
        ItemStack arrow = new ItemStack(Material.ARROW);
        itemMeta = arrow.getItemMeta();
        itemMeta.setDisplayName("§b切换宝石");
        itemMeta = null;
        //第一行 第五行 放置黑色玻璃板
        for (int i = 0;i <= 8;i++){
            inventory.setItem(i,black);
        }
        for (int i = 36;i <= 44;i++){
            inventory.setItem(i,black);
        }
        //放置橙色玻璃板
        inventory.setItem(9,orange);
        inventory.setItem(10,orange);
        inventory.setItem(11,orange);
        inventory.setItem(18,orange);
        inventory.setItem(20,orange);
        inventory.setItem(27,orange);
        inventory.setItem(28,orange);
        inventory.setItem(29,orange);
        //中部
        inventory.setItem(12,blue);
        itemMeta = arrow.getItemMeta();
        itemMeta.setDisplayName("§b点击获取上一个宝石");
        arrow.setItemMeta(itemMeta);
        itemMeta = null;
        inventory.setItem(13,arrow);
        inventory.setItem(14,blue);
        inventory.setItem(21,blue);

        ItemStack anvil = new ItemStack(Material.ANVIL);
        itemMeta = anvil.getItemMeta();
        itemMeta.setDisplayName("§c点击确认取出宝石");
        itemMeta = null;
        inventory.setItem(22,anvil);

        inventory.setItem(23,blue);
        inventory.setItem(30,blue);
        itemMeta = arrow.getItemMeta();
        itemMeta.setDisplayName("§b点击获取下一个宝石");
        arrow.setItemMeta(itemMeta);
        itemMeta = null;
        inventory.setItem(31,arrow);
        inventory.setItem(32,blue);
        //绿色玻璃
        inventory.setItem(15,green);
        inventory.setItem(16,green);
        inventory.setItem(17,green);
        inventory.setItem(24,green);
        inventory.setItem(26,green);
        inventory.setItem(33,green);
        inventory.setItem(34,green);
        inventory.setItem(35,green);
        return inventory;
    }
}
