package me.minignomer.shadowsmp.items;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.items.soulshard.SoulShardManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingRecipes implements Listener {

    public static void addRecipes() {
        ShapedRecipe huntOrb = new ShapedRecipe(new NamespacedKey(ShadowSMP.plugin, "huntorb"), ItemManager.huntOrb);
        huntOrb.shape(" t ", "tst", " t ");
        huntOrb.setIngredient('t', Material.TOTEM_OF_UNDYING);
        huntOrb.setIngredient('s', Material.ECHO_SHARD);
        Bukkit.addRecipe(huntOrb);

        ShapedRecipe smudgeStick = new ShapedRecipe(new NamespacedKey(ShadowSMP.plugin, "smudgestick"), ItemManager.smudgeStick);
        smudgeStick.shape("  s", " b ", "t  ");
        smudgeStick.setIngredient('s', Material.NETHER_STAR);
        smudgeStick.setIngredient('b', Material.BRUSH);
        smudgeStick.setIngredient('t', Material.TOTEM_OF_UNDYING);
        Bukkit.addRecipe(smudgeStick);

        ShapedRecipe revive = new ShapedRecipe(new NamespacedKey(ShadowSMP.plugin, "revive"), ItemManager.revive);
        revive.shape("ese", "nen", "ese");
        revive.setIngredient('e', Material.ECHO_SHARD);
        revive.setIngredient('s', Material.NETHER_STAR);
        revive.setIngredient('n', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(revive);

        ShapedRecipe reRoll = new ShapedRecipe(new NamespacedKey(ShadowSMP.plugin, "reroll"), ItemManager.reRoll);
        reRoll.shape("adg", "ntn", "gda");
        reRoll.setIngredient('a', Material.GOLDEN_APPLE);
        reRoll.setIngredient('d', Material.DIAMOND_BLOCK);
        reRoll.setIngredient('g', Material.GOLD_BLOCK);
        reRoll.setIngredient('n', Material.NETHERITE_INGOT);
        reRoll.setIngredient('t', Material.TOTEM_OF_UNDYING);
        Bukkit.addRecipe(reRoll);
    }

    @EventHandler
    public void onPlayerCraft(PrepareItemCraftEvent e) {
        if (e.getRecipe() == null)
            return;

        Player p = (Player) e.getViewers().get(0);

        System.out.println("Player: " + p);

        if (e.getRecipe().getResult().isSimilar(ItemManager.huntOrb)) {
            ItemStack item = e.getInventory().getItem(5);

            if (item == null || item.getItemMeta() == null) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
                return;
            }
            System.out.println(1);

            if (!item.getItemMeta().hasCustomModelData()) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
                return;
            }

            System.out.println(2);

            if (p.getUniqueId().equals(SoulShardManager.getSoulShardUUID(item))) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
                return;
            }
            System.out.println(3);

            if (item.getItemMeta().getCustomModelData() == SoulShardManager.soulShardInt) {
                return;
            }

            System.out.println(4);

            e.getInventory().setResult(new ItemStack(Material.AIR));
            return;
        }

        if (e.getRecipe().getResult().isSimilar(ItemManager.revive)) {
            int[] soulSlots = { 1, 3, 5, 7, 9 };
            for (int slot : soulSlots) {
                ItemStack item = e.getInventory().getItem(slot);

                if (item == null || item.getItemMeta() == null) {
                    e.getInventory().setResult(new ItemStack(Material.AIR));
                    break;
                }

                if (item.getItemMeta().hasCustomModelData()
                        && item.getItemMeta().getCustomModelData() == SoulShardManager.soulShardInt)
                    continue;

                if (p.getUniqueId().equals(SoulShardManager.getSoulShardUUID(item))) {
                    continue;
                }

                e.getInventory().setResult(new ItemStack(Material.AIR));
                break;
            }
        }
    }
}
