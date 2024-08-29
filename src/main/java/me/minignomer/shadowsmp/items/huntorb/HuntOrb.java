package me.minignomer.shadowsmp.items.huntorb;

import me.minignomer.shadowsmp.ShadowSMP;
import me.minignomer.shadowsmp.abilities.general.HuntOrbEffects;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

import static me.minignomer.shadowsmp.items.ItemManager.huntOrb;

public class HuntOrb extends HuntOrbEffects implements Listener {

    public static HashMap<UUID, Long> usedHuntOrb = new HashMap<>();

    public static final int threeMinutesInMillis = 180000;

    public static boolean isActive(Player p) {
        if (usedHuntOrb.get(p.getUniqueId()) == null) return false;
        if (System.currentTimeMillis() - usedHuntOrb.get(p.getUniqueId()) > threeMinutesInMillis) usedHuntOrb.remove(p.getUniqueId());

        return usedHuntOrb.containsKey(p.getUniqueId());
    }

    public void addHuntOrb(OfflinePlayer p) {
        usedHuntOrb.put(p.getUniqueId(), System.currentTimeMillis());
    }

    public void removeHuntOrb(OfflinePlayer p) {
        usedHuntOrb.remove(p.getUniqueId());
    }

    private void useHuntOrb(Player p) {
        addHuntOrb(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                removeHuntOrb(p);
                if (p.isOnline()) {
                    p.sendMessage("§cYour Hunt Orb effects have run out!");
                }
            }
        }.runTaskLater(ShadowSMP.plugin, 20 * 60 * 2);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!usedHuntOrb.containsKey(e.getPlayer().getUniqueId())) return;
        if (System.currentTimeMillis() - usedHuntOrb.get(e.getPlayer().getUniqueId()) > threeMinutesInMillis) usedHuntOrb.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.isSimilar(huntOrb)) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (isActive(p)) {
            p.sendMessage("§cYou already have a hunt orb active!");
            return;
        }
        applyEffects(p);
        p.sendMessage("§aYou used a Hunt Orb!");
        item.setAmount(item.getAmount() - 1);
        useHuntOrb(p);
    }
}
