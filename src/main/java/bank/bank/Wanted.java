package bank.bank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.UUID;

public class Wanted implements CommandExecutor {
    Bank plugin;

    public Wanted(Bank passedPlugin){
        this.plugin = passedPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(command.getName().equalsIgnoreCase("전과등록")){

        }
        return true;
    }

}
