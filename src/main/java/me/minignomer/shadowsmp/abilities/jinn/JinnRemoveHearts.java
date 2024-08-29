package me.minignomer.shadowsmp.abilities.jinn;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class JinnRemoveHearts extends AbilityManager implements Listener {

    private final List<UUID> players = new ArrayList<>();

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)
            || !(e.getEntity() instanceof Player))
                return;

        Player p = (Player) e.getEntity();

        if (!hasGhost(p, GhostType.JINN))
            return;

        int rand = ThreadLocalRandom.current().nextInt(0, 100);
        if (rand > 5) {
            return;
        }
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(16);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) {
                    players.add(p.getUniqueId());
                    return;
                }
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            }
        }.runTaskLater(ShadowSMP.plugin, 20 * 15);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!players.contains(p.getUniqueId()))
            return;

        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        players.remove(p.getUniqueId());
    }
}
