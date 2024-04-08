package me.minignomer.shadowsmp.items.soulshard;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SoulShardManager extends ItemManager {

    public static int soulShardInt = 100;
    public static NamespacedKey soulShardNamespacedKey = new NamespacedKey(ShadowSMP.plugin, "player");

    public static UUID getSoulShardUUID(ItemStack item) {

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        if (!meta.getPersistentDataContainer().has(SoulShardManager.soulShardNamespacedKey, PersistentDataType.STRING)) return null;

        return UUID.fromString(meta.getPersistentDataContainer().get(SoulShardManager.soulShardNamespacedKey, PersistentDataType.STRING));
    }


    public static ItemStack createSoulShard(Player p) {
        ItemStack item = new ItemStack(Material.ECHO_SHARD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§5§l" + p.getName() + "'s Soul Shard");

        List<String> lore = new ArrayList<>();
        lore.add("§7Use to take one life from " + p.getName());
        meta.setLore(lore);

        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(soulShardNamespacedKey, PersistentDataType.STRING, p.getUniqueId().toString());

        meta.setCustomModelData(soulShardInt);

        item.setItemMeta(meta);

        return item;
    }
}
