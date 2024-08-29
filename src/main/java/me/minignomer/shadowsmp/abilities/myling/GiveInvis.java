package me.minignomer.shadowsmp.abilities.myling;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.abilities.general.SpreadEffects;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class GiveInvis extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline())
                    cancel();
                if (!hasGhost(p, GhostType.MYLING))
                    return;
                if (!SpreadEffects.getNearbyPlayers(p).isEmpty())
                    return;
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 21 * 3, 0, false, false, false));
            }
        }.runTaskTimer(ShadowSMP.plugin, 10, 20 * 3);
    }

}
