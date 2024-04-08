package me.minignomer.shadowsmp.actionbardisplay;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.config.ghosttype.GhostManager;
import me.minignomer.shadowsmp.config.lives.LivesManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DisplayActionBar implements Listener {

    private void displayActionBar(Player p) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                "§3Ghost Type: §r§b§l" + GhostManager.getGhost(p) +
                " §r§0§l┃ " +
                "§5Lives: §r§d§l" + LivesManager.getLives(p) + "/10"));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) cancel();
                displayActionBar(p);
            }
        }.runTaskTimer(ShadowSMP.plugin, 40, 40);
    }
}
