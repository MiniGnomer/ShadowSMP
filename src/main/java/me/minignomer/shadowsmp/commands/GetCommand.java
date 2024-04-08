package me.minignomer.shadowsmp.commands;

import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length != 1)
        {
            p.sendMessage("§c§lIncorrect usage! Correct Usage: /get <item>");
            return true;
        }

        switch (args[0].toUpperCase()) {
            case "REVIVE":
                p.getInventory().addItem(ItemManager.revive);
                break;
            default:
                p.sendMessage("§c§l" + args[0] + " isn't an option for an item!");
                return true;
        }

        return true;
    }
}
