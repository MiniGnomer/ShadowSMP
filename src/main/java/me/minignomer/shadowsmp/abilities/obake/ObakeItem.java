package me.minignomer.shadowsmp.abilities.obake;

import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

public class ObakeItem extends ItemManager implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (!item.isSimilar(obakeItem)) return;
        if (p.hasCooldown(obakeItem.getType())) return;

        p.setCooldown(obakeItem.getType(), 20 * 60 * 10);

        int rand = ThreadLocalRandom.current().nextInt(1, 2 + 1);

        if (rand == 1) {
            p.sendMessage("§aInvisibility has been activated!");
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 60, 0, false, false, false));
            return;
        }

        p.sendMessage("§cGlowing has been activated!");
        p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 60, 0, false, false, false));

    }
}
