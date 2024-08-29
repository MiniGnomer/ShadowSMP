package me.minignomer.shadowsmp.abilities.banshee;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ApplyWeaknessOnKill extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() == null) return;
        Player killer = e.getEntity().getKiller();
        if (!hasGhost(killer, GhostType.BANSHEE)) return;
        if (HuntOrb.isActive(killer)) return;
        killer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 90, 0));
    }
}
