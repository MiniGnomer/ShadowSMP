package me.minignomer.shadowsmp.abilities.phantom;

import me.minignomer.shadowsmp.config.ghosttype.GhostManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PhantomDebuff extends GhostManager implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (getGhost(p) != GhostType.PHANTOM) return;
        if (p.getWorld().getTime() > 12000) return;
        for (Entity entity : p.getNearbyEntities(10, 10, 10)) {
            if (!(entity instanceof Player)) continue;

            p.addPotionEffect(new PotionEffect(
                    PotionEffectType.WEAKNESS,
                    10 * 20,
                    1,
                    false,
                    false,
                    false));
            break;
        }
    }
}
