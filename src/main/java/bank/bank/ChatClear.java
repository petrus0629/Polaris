package bank.bank;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatClear implements CommandExecutor{
    Bank plugin;
    public ChatClear(Bank passedPlugin)
    {
        this.plugin = passedPlugin;
    }

    @Override //채팅청소 플러그인
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("cl"))
        {
            for(int i = 0; i < 200; i++){
                Bukkit.broadcastMessage("");
            }
        }
        return false;
    }
}