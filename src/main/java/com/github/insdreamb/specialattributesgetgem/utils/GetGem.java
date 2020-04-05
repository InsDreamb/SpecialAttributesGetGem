package com.github.insdreamb.specialattributesgetgem.utils;

import com.github.insdreamb.specialattributesgetgem.GemInventory;
import com.github.insdreamb.specialattributesgetgem.Main;
import jerez.Baoshi.Baoshi;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GetGem {

    /** 给玩家发送GUI **/
    public static void sendInventory(Player player){
        if ( !Variable.inventoryMap.containsKey(player.getName()) ) {
            Variable.inventoryMap.put(player.getName(), new GemInventory(Main.getInstance().getVariable().initInventory(player.getName())));
        }
        player.openInventory(Variable.inventoryMap.get(player.getName()).getInventory());
    }

    /** 开始取出 **/
    public void process(InventoryClickEvent event,GemInventory gemInventory){
        if (hasWeapon(event.getInventory().getItem(Variable.WEAOPN))){
            ItemStack weapon = event.getInventory().getItem(Variable.WEAOPN);
            Player player = (Player) event.getWhoClicked();
            if (gemInventory.getGem() == null){
                player.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.process_fault")));
                return;
            }
            if (gemInventory.getLine() == -1 || gemInventory.getGem() == null){
                return;
            }
                ItemStack itemStack = new Baoshi(Main.getInstance().replaceChar(gemInventory.getGem())).getBaoshiItemStack(1);
                if ( itemStack != null) {
                    if (event.getWhoClicked().getInventory().firstEmpty() != -1){
                        if (EconomyUtil.has(player.getName(),Variable.equips.get(gemInventory.getGem()))){
                            Main.getInstance().getLore().changeWeaponLore(gemInventory.getGem(),weapon,gemInventory);
                            event.getWhoClicked().getInventory().addItem(itemStack);
                            EconomyUtil.take(player.getName(),Variable.equips.get(gemInventory.getGem()));
                            player.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.take_money").replace("<money>",Variable.equips.get(gemInventory.getGem()).toString())));
                            player.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.process_success")));
                            gemInventory.setLine(-1);
                            gemInventory.setGem(null);
                            Main.getInstance().getLore().updateLore(event,gemInventory);
                        }
                        else {
                            event.getWhoClicked().closeInventory();
                            gemInventory.setLine(0);
                            player.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.vault_not_enough").replace("<money>",Variable.equips.get(gemInventory.getGem()).toString())));
                        }
                        return;
                    }
                    else {
                        player.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.dont_have_empty")));
                        player.closeInventory();
                    }
                }
                else {
                    player.sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.process_fault")));
                }
        }
        else { ((Player)event.getWhoClicked()).sendMessage(Main.getInstance().replaceChar(Main.getInstance().config.getString("message.weapon_not_found")));
        }
    }

    /** 是否有物品 **/
    public boolean hasWeapon(ItemStack item){
        if (item == null || item.getType() == Material.AIR){
            return false;
        }
        else {
            return true;
        }
    }

}
