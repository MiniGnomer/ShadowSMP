package me.minignomer.shadowsmp.abilities.general;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SmudgeStickEffects extends AbilityManager {

    public void applyEffects(Player user) {
        for (Entity entity : user.getNearbyEntities(30, 15, 30)) {
            if (!(entity instanceof Player)) continue;
            Player p = (Player) entity;
            if (hasGhost(p, GhostType.YUERI))
            {
                applyDebuffPotionEffect(p, PotionEffectType.DARKNESS, 20 * 20, 0);
                applyDebuffPotionEffect(p, PotionEffectType.SLOW, 20 * 20, 9);
                applyDebuffPotionEffect(p, PotionEffectType.SLOW_DIGGING, 20 * 20, 4);
            }
        }
    }
}
