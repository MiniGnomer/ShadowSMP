package me.minignomer.shadowsmp.abilities.oni;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class PreventPearls extends AbilityManager implements Listener {

    @EventHandler
    public void onPearlThrow(ProjectileLaunchEvent e) {
        if (!(e.getEntity() instanceof EnderPearl)) return;
        EnderPearl pearl = (EnderPearl) e.getEntity();
        if (pearl.getShooter() == null || !(pearl.getShooter() instanceof Player)) return;
        Player thrower = (Player) e.getEntity().getShooter();
        if (HuntOrb.isActive(thrower)) return;
        if (!hasGhost(thrower, GhostType.ONI)) return;
        e.setCancelled(true);
    }
}
