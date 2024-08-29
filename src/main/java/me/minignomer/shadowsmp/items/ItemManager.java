package me.minignomer.shadowsmp.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {


    public static ItemStack huntOrb;
    public static ItemStack revive;
    public static ItemStack oniItem;
    public static ItemStack reRoll;
    public static ItemStack smudgeStick;
    public static ItemStack obakeItem;


    public static void registerItems() {
        createReviveItem();
        createHuntOrbItem();
        createOniItem();
        createReRoll();
        createSmudgeStick();
        createObakeItem();
        CraftingRecipes.addRecipes();
    }

    private static void createReviveItem() {
        ItemStack item = new ItemStack(Material.CHAIN_COMMAND_BLOCK);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§1§lRevive Item");
        List<String> lore = new ArrayList<>();
        lore.add("§7Allows you to unban a player");
        lore.add("§7that has been eliminated!");
        m.setLore(lore);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        revive = item;
    }

    private static void createHuntOrbItem() {
        ItemStack item = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§6§lHunt Orb");
        List<String> lore = new ArrayList<>();
        lore.add("§e§nFor 2 minutes:");
        lore.add("§7 - Any percentage buff +10%");
        lore.add("§7 - Any positive effect +1 tier");
        lore.add("§7 - No debuffs");
        m.setLore(lore);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        huntOrb = item;
    }

    private static void createOniItem() {
        ItemStack item = new ItemStack(Material.WHITE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§f§lFull Invisibility");
        List<String> lore = new ArrayList<>();
        lore.add("§7Allows you to go fully invisible for 15 seconds");
        lore.add("§7(including armour + items) every 10 minutes!");
        m.setLore(lore);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        oniItem = item;
    }

    private static void createReRoll() {
        ItemStack item = new ItemStack(Material.YELLOW_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§6§lShadow Re-Roll");
        List<String> lore = new ArrayList<>();
        lore.add("§7Allows you to re-randomize your shadow");
        m.setLore(lore);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        reRoll = item;
    }

    private static void createSmudgeStick() {
        ItemStack item = new ItemStack(Material.BRUSH);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§5§lSmudge Stick");
        List<String> lore = new ArrayList<>();
        lore.add("§7Gain resistance 2 for 10 seconds");
        m.setLore(lore);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        smudgeStick = item;
    }

    private static void createObakeItem() {
        ItemStack item = new ItemStack(Material.PINK_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§d§lObake Item");
        List<String> lore = new ArrayList<>();
        lore.add("§7Give you a 50% chance to get either");
        lore.add("§7invisibility or glowing for 1 minute");
        m.setLore(lore);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        obakeItem = item;
    }
}
