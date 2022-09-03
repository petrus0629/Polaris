package bank.bank;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

public class MoneyMaker implements CommandExecutor {
    Bank plugin;
    public MoneyMaker(Bank passedPlugin)
    {
        this.plugin = passedPlugin;
    }


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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("현금발행")) {
            if (args.length != 2) {
                sender.sendMessage("=========================================================================");
                sender.sendMessage(ChatColor.BOLD + "<! 현금발행 방법 !>");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.BOLD+ "주의 : 이 시스템은 금융업에 종사하는 플레이어만 사용 가능합니다.");
                sender.sendMessage("사용예시 : /현금발행 <발행할 금액> <발행할 수량>");
                sender.sendMessage("=========================================================================");

                // 커맨드를 실행 했을 때 생기는 잡시리한 경고문을 없애주는 예외처리
                int money = 0;
                int num = 0;
                Player p = (Player) sender;
                try {
                    money = Integer.parseInt(args[0]);
                    num = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    p.sendMessage("");
                    return true;
                }

                /* 커스텀 아이템 */
                ItemStack paper = new ItemStack(Material.PAPER);
                ItemMeta meta = paper.getItemMeta();
                meta.setDisplayName("[지폐] : "+ args[0] + "원");
                meta.setLore(Arrays.asList(ChatColor.WHITE+args[0]+"원 입니다.", "발행기관 : 폴라리스 중앙은행", ChatColor.BOLD + "화폐위조는 중범죄 입니다."));
                paper.setItemMeta(meta);

                /* 수량에 맞게 입력된 현금을 통장에 보관하는 커맨드 */
                for (int i = 0; i < num; i++) {
                    p.getInventory().addItem(paper);
                }
                econ.depositPlayer(p, money);
                p.sendMessage("====================");
                p.sendMessage("돈이 추가되었습니다.");
                p.sendMessage(ChatColor.YELLOW+"현재 잔고 : "+ econ.getBalance(p)); // econ.getBalance는 플레이어의 잔액을 표시한다.
                p.sendMessage("====================");
            }
        }

        /* 발행된 현금을 삭제하는 커맨드 */
        if(command.getName().equalsIgnoreCase("현금삭제")){
            if(args.length != 2){
                sender.sendMessage("=========================================================================");
                sender.sendMessage(ChatColor.BOLD + "<! 현금삭제 방법 !>");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.BOLD+ "주의 : 이 시스템은 금융업에 종사하는 플레이어만 사용 가능합니다.");
                sender.sendMessage("사용예시 : /현금삭제 <삭제할 금액> <삭제할 수량>");
                sender.sendMessage("=========================================================================");
            }
        }
/*
        // 커맨드를 실행 했을 때 생기는 잡시리한 경고문을 없애주는 예외처리
        int money = 0;
        int num = 0;
        Player p = (Player) sender;
        try {
            money = Integer.parseInt(args[0]);
            num = Integer.parseInt(args[1]);
        } catch (Exception e) {
            p.sendMessage("");
            return true;
        }

        */
/* 커스텀 아이템 *//*

        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();
        meta.setDisplayName("[지폐] : "+ args[0] + "원");
        meta.setLore(Arrays.asList(ChatColor.WHITE+args[0]+"원 입니다.", "발행기관 : 폴라리스 중앙은행", ChatColor.BOLD + "화폐위조는 중범죄 입니다."));
        paper.setItemMeta(meta);

        */
/* 수량에 맞게 입력된 현금을 통장에 보관하는 커맨드 *//*

        for (int i = 0; i < num; i++) {
            p.getInventory().addItem(paper);
        }
        econ.depositPlayer(p, money);
        p.sendMessage("====================");
        p.sendMessage("돈이 추가되었습니다.");
        p.sendMessage(ChatColor.YELLOW+"현재 잔고 : "+ econ.getBalance(p)); // econ.getBalance는 플레이어의 잔액을 표시한다.
        p.sendMessage("====================");
*/

        return false;
    }
}
