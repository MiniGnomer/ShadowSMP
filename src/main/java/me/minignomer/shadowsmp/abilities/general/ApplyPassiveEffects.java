package me.minignomer.shadowsmp.abilities.general;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ApplyPassiveEffects extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        applyPassiveEffects(p);
    }
}
