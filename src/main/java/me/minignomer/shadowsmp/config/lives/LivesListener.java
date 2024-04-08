package me.minignomer.shadowsmp.config.lives;

import me.minignomer.shadowsmp.items.soulshard.SoulShardManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class LivesListener extends LivesManager implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (getLives(e.getPlayer()) <= 0) {
            // Banned
            e.setQuitMessage(null);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!getConfig().contains("Lives." + p.getUniqueId())) {
            // First time joining
            setLives(p, 5);
        }
        if (getLives(p) <= 0) {
            // Banned
            e.setJoinMessage(null);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.kickPlayer("§c§lYou have no more lives!");
                }
            }.runTaskLater(getPlugin(), 1L);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        // Drop item
        p.getWorld().dropItem(p.getLocation(), SoulShardManager.createSoulShard(p));
    }
}
