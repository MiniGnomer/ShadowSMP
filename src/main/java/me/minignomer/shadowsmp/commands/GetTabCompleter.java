package me.minignomer.shadowsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GetTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> options = new ArrayList<>();
        if (args.length == 1) {
            options.add("Revive");
            options.add("Hunt_Orb");
            options.add("Oni_Item");
            options.add("ReRoll");
            options.add("Smudge_Stick");
            options.add("Obake_Item");
        }
        return options;
    }
}
