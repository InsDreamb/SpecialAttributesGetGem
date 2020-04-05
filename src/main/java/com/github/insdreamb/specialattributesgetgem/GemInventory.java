package com.github.insdreamb.specialattributesgetgem;

import org.bukkit.inventory.Inventory;

public class GemInventory {
    private Inventory inventory;
    private int line = -1;
    private String gem;

    public GemInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getGem() {
        return gem;
    }

    public void setGem(String gem) {
        this.gem = gem;
    }

}