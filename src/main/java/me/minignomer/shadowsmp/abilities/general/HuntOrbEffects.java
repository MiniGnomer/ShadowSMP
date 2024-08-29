package me.minignomer.shadowsmp.abilities.general;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class HuntOrbEffects extends AbilityManager {

    public void applyEffects(Player user) {
        for (Entity entity : user.getNearbyEntities(50, 30, 50)) {
            if (!(entity instanceof Player)) continue;
            Player p = (Player) entity;
            if (hasGhost(p, GhostType.REVENANT))
            {
                applyBuffPotionEffect(p, PotionEffectType.SPEED, 20 * 60, 2);
                continue;
            }
            if (hasGhost(p, GhostType.YOKAI))
            {
                applyDebuffPotionEffect(p, PotionEffectType.DARKNESS, 20 * 45, 0);
            }
        }
    }

}
