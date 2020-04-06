package com.github.insdreamb.specialattributesgetgem.listener;

import com.github.insdreamb.specialattributesgetgem.GemInventory;
import com.github.insdreamb.specialattributesgetgem.Main;
import com.github.insdreamb.specialattributesgetgem.task.AnvilLore;
import com.github.insdreamb.specialattributesgetgem.utils.Lore;
import com.github.insdreamb.specialattributesgetgem.utils.Variable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        if (event.getInventory().getTitle().equalsIgnoreCase("§b取宝石界面")){
            int rawSlot = event.getRawSlot();
            if (rawSlot >= 45 || rawSlot == Variable.WEAOPN ){
                GemInventory gemInventory = Variable.inventoryMap.get(event.getWhoClicked().getName());
                Lore.checkClick(event);
                new AnvilLore(event,gemInventory);
                return;
            }
            if (rawSlot == Variable.FIRST_ARROW){
                GemInventory gemInventory = Variable.inventoryMap.get(event.getWhoClicked().getName());
                Main.getInstance().getLore().up(event,gemInventory);
                event.setCancelled(true);
                return;
            }
            if (rawSlot == Variable.Second_ARROW){
                GemInventory gemInventory = Variable.inventoryMap.get(event.getWhoClicked().getName());
                Main.getInstance().getLore().down(event,gemInventory);
                event.setCancelled(true);
                return;
            }
            if (rawSlot == Variable.ANVIL){
                GemInventory gemInventory = Variable.inventoryMap.get(event.getWhoClicked().getName());
                Main.getInstance().getGetGem().process(event,gemInventory);
                event.setCancelled(true);
                return;
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent event){
        if (event.getInventory().getTitle().equalsIgnoreCase("§b取宝石界面")){
            ItemStack weapon = event.getInventory().getItem(Variable.WEAOPN);
            if (weapon != null && weapon.getType() != Material.AIR){
                        Player player = (Player) event.getPlayer();
                        Variable.inventoryMap.remove(event.getPlayer().getName());
                        player.getInventory().addItem(weapon);
            }
        }
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
            ItemStack weapon = Variable.inventoryMap.get(player.getName()).getInventory().getItem(Variable.WEAOPN);
            if (weapon != null && weapon.getType() != Material.AIR){
                Variable.inventoryMap.remove(player.getName());
                player.getInventory().addItem(weapon);
            }
    }
}
