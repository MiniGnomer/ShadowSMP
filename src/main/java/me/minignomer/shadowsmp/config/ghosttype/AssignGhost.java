package me.minignomer.shadowsmp.config.ghosttype;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AssignGhost extends GhostManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (getConfig().contains("Type." + p.getUniqueId())) return;
        assignRandomGhost(p);
    }
}
