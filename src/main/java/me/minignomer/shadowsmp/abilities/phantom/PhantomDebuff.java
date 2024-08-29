package me.minignomer.shadowsmp.abilities.phantom;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PhantomDebuff extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!hasGhost(p, GhostType.PHANTOM)) return;
        if (HuntOrb.isActive(p)) return;
        if (p.getWorld().getTime() > 12000) return;
        for (Entity entity : p.getNearbyEntities(10, 10, 10)) {
            if (!(entity instanceof Player)) continue;
            applyDebuffPotionEffect(p, PotionEffectType.WEAKNESS, 20 * 10, 0);
            break;
        }
    }

    @EventHandler
    public void onPlayerHurt(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        if (HuntOrb.isActive(p)) return;

        if (!hasGhost(p, GhostType.PHANTOM)) return;
        if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) return;

        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        applyDebuffPotionEffect(p, PotionEffectType.SLOW, 20 * 10, 0);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) return;
                if (!hasGhost(p, GhostType.PHANTOM)) return;
                applyPassiveEffects(p);
            }
        }.runTaskLater(ShadowSMP.plugin, 20 * 10);
    }
}
