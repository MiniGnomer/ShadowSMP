package me.minignomer.shadowsmp.abilities.oni;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class OniItem extends ItemManager implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (!item.isSimilar(oniItem)) return;
        if (p.hasCooldown(oniItem.getType())) return;


        p.setCooldown(oniItem.getType(), 20 * 60 * 10);

        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for (Player onlinePlayer : players) {
            onlinePlayer.hidePlayer(ShadowSMP.plugin, p);
        }

        p.sendMessage("§aFull Invisibility has been activated!");

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player onlinePlayer : players) {
                    onlinePlayer.showPlayer(ShadowSMP.plugin, p);
                }
                p.sendMessage("§cFull Invisibility has been deactivated!");
            }
        }.runTaskLater(ShadowSMP.plugin, 20 * 15);
    }
}
