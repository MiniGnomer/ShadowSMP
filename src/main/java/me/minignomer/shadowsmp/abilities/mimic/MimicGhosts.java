package me.minignomer.shadowsmp.abilities.mimic;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MimicGhosts extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerHurt(EntityDamageByEntityEvent e) {
        if (
            !(e.getEntity() instanceof Player) ||
            !(e.getDamager() instanceof Player)
        )
            return;

        Player damager = (Player) e.getDamager();
        Player damagedPlayer = (Player) e.getEntity();

        if (!isMimic(damager))
            return;

        assignGhost(damager, getGhost(damagedPlayer), true);
    }
}
