package me.minignomer.shadowsmp.abilities.general;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpreadEffects extends AbilityManager implements Listener {

    public static List<Player> getNearbyPlayers(Player p) {
        List<Player> players = new ArrayList<>();
        for (Entity entity : p.getNearbyEntities(15, 15, 15)) {
            if (!(entity instanceof Player)) continue;
            players.add((Player) entity);
        }

        return players;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) cancel();
                List<Player> nearbyPlayers = getNearbyPlayers(p);
                if (nearbyPlayers.isEmpty()) return;
                if (hasGhost(p, GhostType.DEOGEN)) {
                    for (Player nearbyPlayer : nearbyPlayers) {
                        nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 4, 0, false, false, false));
                    }
                    return;
                }
                if (hasGhost(p, GhostType.GORYO)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 4, 0, false, false, false));
                    return;
                }
                if (hasGhost(p, GhostType.YUERI)) {
                    for (Player nearbyPlayer : nearbyPlayers) {
                        nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * 4, 0, false, false, false));
                    }
                }
            }
        }.runTaskTimer(ShadowSMP.plugin, 20L, 20 * 3);
    }
}
