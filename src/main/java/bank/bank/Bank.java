package bank.bank;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import static sun.audio.AudioPlayer.player;


public class Bank extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {

        /* Vault 플러그인 연동 구문 */
        if (!setupEconomy()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        System.out.println("-------" + 10);
        System.out.println("Plugin's On");
        System.out.println("-------" + 10);

        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("cl").setExecutor(new ChatClear(this));
        this.getCommand("현금발행").setExecutor(new MoneyMaker(this));

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("이름조회")){
            Player p = (Player) sender;

            p.sendMessage(p.getName());
        }

        return true;
    }

    // Vault API를 이용한 구문
    private static Economy econ = null;
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