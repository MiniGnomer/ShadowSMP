package me.minignomer.shadowsmp.items.smudgestick;

import me.minignomer.shadowsmp.abilities.general.SmudgeStickEffects;
import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SmudgeStick extends SmudgeStickEffects implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.isSimilar(ItemManager.smudgeStick)) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (p.hasCooldown(item.getType())) return;
        p.sendMessage("§aYou used a Smudge Stick!");
        item.setAmount(item.getAmount() - 1);
        applyEffects(p);
        p.setCooldown(item.getType(), 20 * 30);
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 1, false, false, false));
    }
}
