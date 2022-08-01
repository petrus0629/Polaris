package bank.bank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class connectionCount extends JavaPlugin implements Listener {
    Bank plugin;
    int count = 1;

    public connectionCount(Bank passedPlugin) {
        this.plugin = passedPlugin;
    }

    @EventHandler
    public void join(PlayerJoinEvent e, Command sender){
        Player p = (Player) sender;
        e.setJoinMessage(p.getName() + "님의 " + count + "번째 입장 입니다.!");
        count = count + 1;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        return false;
    }
}

