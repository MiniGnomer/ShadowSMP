package me.minignomer.shadowsmp.abilities.onryo;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DimensionEffects extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) cancel();
                if (hasGhost(p, GhostType.ONRYO)) {
                    if (p.getWorld().getEnvironment() == World.Environment.NORMAL) {
                        applyDebuffPotionEffect(p, PotionEffectType.WEAKNESS, 20 * 6, 0);
                    } else if (p.getWorld().getEnvironment() == World.Environment.NETHER) {
                        applyBuffPotionEffect(p, PotionEffectType.INCREASE_DAMAGE, 20 * 6, 1);
                    }
                }
            }
        }.runTaskTimer(ShadowSMP.plugin, 10, 20 * 5);
    }
}
