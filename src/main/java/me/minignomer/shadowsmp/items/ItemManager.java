package me.minignomer.shadowsmp.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {


    public static ItemStack reRoll;
    public static ItemStack revive;


    public static void registerItems() {
        createReviveItem();
    }

    private static void createReviveItem() {
        ItemStack item = new ItemStack(Material.CHAIN_COMMAND_BLOCK);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§1§lRevive Item");
        List<String> lore = new ArrayList<>();
        lore.add("§9§lAllows you to unban a player");
        lore.add("§9§lthat has been eliminated!");
        m.setLore(lore);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        revive = item;
    }
}
