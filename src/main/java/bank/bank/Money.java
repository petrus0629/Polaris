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

public class Money implements CommandExecutor {
    Bank plugin;

    public Money(Bank passedPlugin) {
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
        if (command.getName().equalsIgnoreCase("현금")) {
            sender.sendMessage("=========================================================================");
            sender.sendMessage(ChatColor.BOLD + "<! 통화량 조정 플러그인 !>");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.BOLD + "주의 : 이 시스템은 금융업에 종사하는 플레이어만 사용 가능합니다.");
            sender.sendMessage("통화발행 사용예시 : /현금 발행 <발행할 금액> <발행할 수량>");
            sender.sendMessage("통화삭제 사용예시 : /현금 삭제 <발행할 금액> <발행할 수량>");
            sender.sendMessage("=========================================================================");
            if (args.length != 3) {
                if (args[0].equalsIgnoreCase("발행")) {
                    int money = 0;
                    int num = 0;
                    Player p = (Player) sender;
                    try {
                        money = Integer.parseInt(args[1]);
                        num = Integer.parseInt(args[2]);
                    } catch (Exception e) {
                        p.sendMessage("");
                        return true;
                    }

                    ItemStack paperMoney = new ItemStack(Material.PAPER);
                    ItemMeta meta = paperMoney.getItemMeta();
                    meta.setDisplayName("[지폐] : " + args[1]);
                    meta.setLore(Arrays.asList(ChatColor.WHITE + args[1] + "원 입니다.", "발행기관 폴라리스 중앙은행", ChatColor.BOLD + "(경고 : 화폐위조는 중범죄 입니다."));
                    paperMoney.setItemMeta(meta);

                    for (int i = 0; i < num; i++) {
                        p.getInventory().addItem(paperMoney);
                    }
                    econ.depositPlayer(p, money);
                    p.sendMessage("====================");
                    p.sendMessage("돈이 추가되었습니다.");
                    p.sendMessage(ChatColor.YELLOW + "현재 잔고 : " + econ.getBalance(p)); // econ.getBalance는 플레이어의 잔액을 표시한다.
                    p.sendMessage("====================");
                }
                if (args[0].equalsIgnoreCase("삭제")) {
                    int money, num = 0;
                    Player p = (Player) sender;
                    try {
                        money = Integer.parseInt(args[1]);
                        num = Integer.parseInt(args[2]);
                    } catch (Exception e) {
                        p.sendMessage("");
                        return true;
                    }
                    ItemStack paperMoney = new ItemStack(Material.PAPER);
                    ItemMeta meta = paperMoney.getItemMeta();
                    meta.setDisplayName("[지폐] : " + args[1]);
                    meta.setLore(Arrays.asList(ChatColor.WHITE + args[1] + "원 입니다.", "발행기관 폴라리스 중앙은행", ChatColor.BOLD + "(경고 : 화폐위조는 중범죄 입니다."));
                    paperMoney.setItemMeta(meta);

                    for (int i = 0; i < num; i--) {
                        p.getInventory().addItem(paperMoney);
                    }
                    econ.depositPlayer(p, money);
                    p.sendMessage("====================");
                    p.sendMessage("돈이 추가되었습니다.");
                    p.sendMessage(ChatColor.YELLOW + "현재 잔고 : " + econ.getBalance(p)); // econ.getBalance는 플레이어의 잔액을 표시한다.
                    p.sendMessage("====================");
                }
            }
            return false;
        }
        return false;
    }
}
