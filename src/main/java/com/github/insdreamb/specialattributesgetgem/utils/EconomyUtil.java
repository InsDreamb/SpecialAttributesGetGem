package com.github.insdreamb.specialattributesgetgem.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class EconomyUtil {
    private static Economy economy;

    private EconomyUtil(){
        throw new Error("不可实例化");
    }

    public static boolean setupEconomy() {
        try {
            RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
            if (economyProvider != null) {
                economy = economyProvider.getProvider();
            }
        }
        catch (Exception e){
            return false;
        }
        return economy != null;
    }

    public static void take(String player, double money) {
        economy.withdrawPlayer(player, money);
    }

    public static double getBalance(String player) {
        return economy.getBalance(player);
    }

    public static boolean has(String player, double money) {
        return (getBalance(player) >= money);
    }

}