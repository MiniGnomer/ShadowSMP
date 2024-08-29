package me.minignomer.shadowsmp.abilities.poltergeist;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class IncreaseBowDamage extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (
            !(e.getDamager() instanceof Arrow) &&
            !(e.getDamager() instanceof SpectralArrow))
                return;

        Projectile projectile = (Projectile) e.getDamager();

        if (!(projectile.getShooter() instanceof Player))
            return;

        Player shooter = (Player) projectile.getShooter();

        if (!hasGhost(shooter, GhostType.POLTERGEIST))
            return;

        e.setDamage(e.getDamage() * 1.5);
    }
}
