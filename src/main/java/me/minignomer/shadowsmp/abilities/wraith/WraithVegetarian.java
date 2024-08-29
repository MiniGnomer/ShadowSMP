package me.minignomer.shadowsmp.abilities.wraith;

import me.minignomer.shadowsmp.abilities.AbilityManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Arrays;

public class WraithVegetarian extends AbilityManager implements Listener {

    private final Material[] meats = new Material[] {
            Material.BEEF,
            Material.COOKED_BEEF,
            Material.PORKCHOP,
            Material.COOKED_PORKCHOP,
            Material.MUTTON,
            Material.COOKED_MUTTON,
            Material.CHICKEN,
            Material.COOKED_CHICKEN,
            Material.RABBIT,
            Material.COOKED_RABBIT,
            Material.COD,
            Material.COOKED_COD,
            Material.SALMON,
            Material.COOKED_SALMON,
            Material.TROPICAL_FISH,
            Material.PUFFERFISH,
            Material.ROTTEN_FLESH,
            Material.SPIDER_EYE,
            Material.RABBIT_STEW };

    @EventHandler
    public void onFoodConsume(PlayerItemConsumeEvent e) {
        if (!hasGhost(e.getPlayer(), GhostType.WRAITH)) return;
        if (HuntOrb.isActive(e.getPlayer())) return;
        if (Arrays.asList(meats).contains(e.getItem().getType())) e.setCancelled(true);
    }
}
