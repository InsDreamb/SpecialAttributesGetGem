package com.github.insdreamb.specialattributesgetgem.task;

import com.github.insdreamb.specialattributesgetgem.GemInventory;
import com.github.insdreamb.specialattributesgetgem.Main;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AnvilLore {
    public AnvilLore(InventoryClickEvent event, GemInventory gemInventory){
        new BukkitRunnable(){
            @Override
            public void run() {
                Main.getInstance().getLore().updateLore(event,gemInventory);
                this.cancel();
            }
        }.runTaskLater(Main.getInstance(),4);
    }
}
