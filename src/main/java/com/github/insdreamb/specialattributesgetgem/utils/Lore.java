package com.github.insdreamb.specialattributesgetgem.utils;

import com.github.insdreamb.specialattributesgetgem.GemInventory;
import com.github.insdreamb.specialattributesgetgem.Main;
import jerez.Baoshi.Baoshi;
import jerez.Kong.Kong;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Lore {

    public ItemMeta setMeta(ItemStack dispose,String str){
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = dispose.getItemMeta();
        lore.add(str);
        itemMeta.setLore(lore);
        return itemMeta;
    }

    /** 设置物品不能取出宝石Lore描述 **/
    public void setDisposeCantgetGem(Inventory inventory){
        ItemStack anvil = clearLore();
        anvil.setItemMeta(setMeta(anvil, "§c该物品取不了宝石!"));
        inventory.setItem(Variable.ANVIL, anvil);
        inventory.setItem(Variable.GEM,new ItemStack(Material.AIR));
    }

    /** 放入物品或者拿出物品更新Lore **/
    public void updateLore(InventoryClickEvent event,GemInventory gemInventory){
        gemInventory.setLine(-1);
        editGetGemIronLore(event,gemInventory,false);
    }

    /** 点击上一个宝石的按钮 **/
    public void up(InventoryClickEvent event, GemInventory gemInventory){
        editGetGemIronLore(event,gemInventory,true);
    }

    /** 点击下一个宝石的按钮 **/
    public void down(InventoryClickEvent event,GemInventory gemInventory){
        editGetGemIronLore(event,gemInventory,false);
    }

    private void editGetGemIronLore(InventoryClickEvent event,GemInventory gemInventory,boolean reverse){
        ItemStack item = event.getInventory().getItem(Variable.WEAOPN);
        ItemStack anvil = clearLore();
        ItemMeta itemMeta = anvil.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (item != null) {
            String str = hasGem(event,item,gemInventory,reverse);
            if (str != null) {
                lore.add("§e 需要金钱: " + Variable.equips.get(str));
                lore.add("§b 宝石名: " + str +"§7——第"+(gemInventory.getLine()+1)+"行");
                event.getInventory().setItem(Variable.GEM,new Baoshi(Main.getInstance().replaceChar(gemInventory.getGem())).getBaoshiItemStack(1));
                itemMeta.setLore(lore);
                anvil.setItemMeta(itemMeta);
                event.getInventory().setItem(Variable.ANVIL, anvil);
            }
            else {
                setDisposeCantgetGem(event.getInventory());
            }
        }
        else {
            setDisposeCantgetGem(event.getInventory());
        }
    }
    /** 更改武器Lore **/
    public boolean changeWeaponLore(String baoshiName,ItemStack weaponItemStack,GemInventory gemInventory) {
        Baoshi baoshi = new Baoshi(baoshiName);
        String xiangqianName = baoshi.getXiangqianKong();
        String kongName = Kong.getKongLore(xiangqianName);
        List<String> loreList = weaponItemStack.getItemMeta().getLore();
        int var = gemInventory.getLine();
        if (loreList.get(var).contains(baoshiName)) {
            loreList.set(var, kongName);
            int i = Main.getInstance().body.getStringList("item." + baoshiName + ".要替换的属性").size();
            for (int var1 = 0; var1 < i; var1++) {
                loreList.remove(var + 1);
            }
            ItemMeta itemMeta = weaponItemStack.getItemMeta();
            itemMeta.setLore(loreList);
            weaponItemStack.setItemMeta(itemMeta);
            return true;
        }
        return false;
    }

    /** 清除发射器的Lore **/
    public ItemStack clearLore(){
        ItemStack anvil = new ItemStack(Material.ANVIL);
        ItemMeta itemMeta = anvil.getItemMeta();
        itemMeta.setDisplayName("§c点击确认取出宝石");
        anvil.setItemMeta(itemMeta);
        return anvil;
    }

    /** 判断是不是包含宝石 **/
    public String hasGem(InventoryClickEvent event,ItemStack item,GemInventory gemInventory,boolean reverse) {
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            List<String> lore = item.getItemMeta().getLore();
            int size = 0;
            for (String key : Variable.equips.keySet()) {
                size++;
                int i;
                if (reverse){
                    if (gemInventory.getLine() >= 1) {
                        for (i = gemInventory.getLine() - 1; i != 0; i--) {
                            if (Main.getInstance().replaceChar(lore.get(i)).contains(key)) {
                                gemInventory.setLine(i);
                                gemInventory.setGem(key);
                                return key;
                            }
                        }
                    }
                }
                else {
                    if (gemInventory.getLine() + 1 <= lore.size()) {
                        for (i = gemInventory.getLine() + 1; i < lore.size(); i++) {
                            if (Main.getInstance().replaceChar(lore.get(i)).contains(key)) {
                                gemInventory.setLine(i);
                                gemInventory.setGem(key);
                                return key;
                            }
                        }
                    }
                }
                if (size == Variable.equips.size()){
                    gemInventory.setLine(-1);
                }
            }
            gemInventory.setGem(null);
        }
        return null;
    }

    /** 防止双击强摘展示的宝石 **/
    public static void checkClick(InventoryClickEvent event){
        ItemStack cursor = event.getCursor();
        ItemStack gem = event.getInventory().getItem(Variable.GEM);
        if ( !(cursor == null || cursor.getType() == Material.AIR) || !(gem == null || gem.getType() == Material.AIR)){
            return;
        }
        if (cursor.hasItemMeta() && cursor.getItemMeta().hasDisplayName()) {
            if (gem.hasItemMeta() && gem.getItemMeta().hasDisplayName() && gem.getItemMeta().getDisplayName().contains(cursor.getItemMeta().getDisplayName())) {
                event.setCancelled(true);
                event.getWhoClicked().closeInventory();
                GetGem.sendInventory((Player) event.getWhoClicked());
            }
        }
    }
}
