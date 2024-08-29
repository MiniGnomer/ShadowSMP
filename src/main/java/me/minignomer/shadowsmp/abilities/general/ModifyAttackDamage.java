package me.minignomer.shadowsmp.abilities.general;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ModifyAttackDamage extends AbilityManager implements Listener {

    @EventHandler
    public void onPlayerDamageByPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) return;
        Player damager = (Player) e.getDamager();
        Player p = (Player) e.getEntity();
        double damageModifier;
        switch (getGhost(damager)) {
            // Attack
            case SHADE:
            case DEOGEN:
                if (HuntOrb.isActive(damager)) return;
                damageModifier = e.getDamage() * -0.15;
                break;

            case YOKAI:
                if (!damager.getInventory().getItemInMainHand().getType().name().contains("SWORD")) return;
                damageModifier = e.getDamage() * 0.1;

                if (HuntOrb.isActive(damager)) {
                    damageModifier += e.getDamage() * 0.1;
                }
                break;

            case BANSHEE:
                double health = p.getHealth();
                if (health > 10) return;
                damageModifier = e.getDamage() / (0.25 * health);
                if (HuntOrb.isActive(damager)) {
                    damageModifier += e.getDamage() * 0.1;
                }
                break;

            case POLTERGEIST:
                if (HuntOrb.isActive(damager)) return;
                if (damager.getInventory().contains(Material.BOW)
                    || damager.getInventory().contains(Material.CROSSBOW))
                        return;
                damageModifier = e.getDamage() * -0.1;
                break;

            default:
                return;
        }

        e.setDamage(e.getDamage() + damageModifier);

    }
}
