package me.minignomer.shadowsmp.items.reroll;

import me.minignomer.shadowsmp.config.ghosttype.GhostManager;
import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ReRoll extends GhostManager implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null || !e.getItem().isSimilar(ItemManager.reRoll)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        ItemStack item = e.getItem();
        if (p.hasCooldown(item.getType())) {
            p.sendMessage("§c§lYou can't use this item yet!");
            return;
        }
        p.setCooldown(item.getType(), 20 * 5);
        item.setAmount(item.getAmount() - 1);

        assignRandomGhost(p);
    }
}
