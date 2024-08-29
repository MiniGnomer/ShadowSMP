package me.minignomer.shadowsmp.abilities.general;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.abilities.AbilityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeEffects extends AbilityManager implements Listener {

    public void startTimeEffects(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) cancel();
                long time = p.getWorld().getTime();
                if (time > 12000) {
                    // Night
                    switch (getGhost(p)) {
                        case MARE:
                            applyBuffPotionEffect(p, PotionEffectType.INCREASE_DAMAGE, 20 * 12, 1);
                            break;
                        case HANTU:
                            applyBuffPotionEffect(p, PotionEffectType.SPEED, 20 * 12, 1);
                            break;
                    }
                    return;
                }
                // Day
                switch (getGhost(p)) {
                    case MARE:
                    case HANTU:
                        applyDebuffPotionEffect(p, PotionEffectType.SLOW, 20 * 12, 1);
                        return;
                    case RAIJU:
                        applyBuffPotionEffect(p, PotionEffectType.SPEED, 20 * 12, 1);
                }
            }
        }.runTaskTimer(ShadowSMP.plugin, 20, 20 * 10);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        startTimeEffects(p);
    }
}
