package me.minignomer.shadowsmp.abilities.phantom;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.config.ghosttype.GhostManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PhantomBuff extends GhostManager implements Listener {

    private void applyBuff(Player p) {
        if (getGhost(p) != GhostType.PHANTOM) return;
        p.addPotionEffect(new PotionEffect(
                PotionEffectType.INVISIBILITY,
                PotionEffect.INFINITE_DURATION,
                0,
                false,
                false,
                false));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // Apply invisibility
        Player p = e.getPlayer();
        applyBuff(p);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        // Apply invisibility
        new BukkitRunnable() {
            @Override
            public void run() {
                applyBuff(e.getPlayer());
            }
        }.runTaskLater(ShadowSMP.plugin, 1L);
    }
}
