package bank.bank;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Bank extends JavaPlugin implements Listener {

    private static Economy econ = null;

    @Override
    public void onEnable() {

        if (!setupEconomy()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        System.out.println("-------" + 10);
        System.out.println("Plugin's On");
        System.out.println("-------" + 10);

        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("cl").setExecutor(new ChatClear(this));
        this.getCommand("현금발행").setExecutor(new Money(this));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}