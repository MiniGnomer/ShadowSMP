package me.minignomer.shadowsmp.abilities.general;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ModifyDefenceDamage extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerDamageByPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) return;
        Player damager = (Player) e.getDamager();
        Player p = (Player) e.getEntity();
        double damageModifier;
        switch (getGhost(p)) {
            // Defence
            case DEMON:
                if (HuntOrb.isActive(damager)) return;
                damageModifier = e.getDamage() * 0.25;
                if (damager.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
                    damageModifier += e.getDamage() * 0.40;
                }
                break;

            case SHADE:
                damageModifier = e.getDamage() * -0.05;
                if (HuntOrb.isActive(p)) {
                    damageModifier += e.getDamage() * -0.1;
                }
                break;

            default:
                return;
        }

        e.setDamage(e.getDamage() + damageModifier);

    }
}
