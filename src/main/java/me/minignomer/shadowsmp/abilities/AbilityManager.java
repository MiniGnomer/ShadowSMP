package me.minignomer.shadowsmp.abilities;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.config.ghosttype.GhostManager;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public abstract class AbilityManager extends GhostManager {

    public void applyBuffPotionEffect(Player p, PotionEffectType potionEffectType, int tickDuration, int amplifier) {
        if (HuntOrb.isActive(p)) {
            p.addPotionEffect(new PotionEffect(
                    potionEffectType,
                    tickDuration,
                    amplifier + 1,
                    false,
                    false,
                    false));
            return;
        }

        p.addPotionEffect(new PotionEffect(
                potionEffectType,
                tickDuration,
                amplifier,
                false,
                false,
                false));
    }

    public void applyDebuffPotionEffect(Player p, PotionEffectType potionEffectType, int tickDuration, int amplifier) {
        if (HuntOrb.isActive(p)) return;
        p.addPotionEffect(new PotionEffect(
                potionEffectType,
                tickDuration,
                amplifier,
                false,
                false,
                false));
    }

    public void applyPassiveEffects(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) cancel();
                HashMap<PotionEffectType, Integer> positiveEffects = getPositiveGhostEffects(p);
                if (positiveEffects != null) {
                    for (PotionEffectType potionType : positiveEffects.keySet()) {
                        applyBuffPotionEffect(p, potionType, 20 * 4, positiveEffects.get(potionType));
                    }
                }
                HashMap<PotionEffectType, Integer> negativeEffects = getNegativeGhostEffects(p);
                if (negativeEffects != null) {
                    for (PotionEffectType potionType : negativeEffects.keySet()) {
                        applyDebuffPotionEffect(p, potionType, 20 * 4, negativeEffects.get(potionType));
                    }
                }
            }
        }.runTaskTimer(ShadowSMP.plugin, 0, 20 * 3);
    }
}
