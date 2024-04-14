package me.minignomer.shadowsmp.items.soulshard;

import me.minignomer.shadowsmp.config.lives.LivesManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class SoulShardListener extends LivesManager implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        ItemStack heldItem = e.getItem();
        if (heldItem == null) return;
        UUID uuid = SoulShardManager.getSoulShardUUID(heldItem);
        if (uuid == null) return;

        OfflinePlayer killedPlayer = Bukkit.getOfflinePlayer(uuid);

        if (getLives(killedPlayer) < 1) {
            p.sendMessage("§c§lThat player is already eliminated!");
            return;
        }

        if (killedPlayer.equals(p)) {
            p.sendMessage("§c§lYou can't steal your own lives!");
            return;
        }

        addLives(p, 1);
        heldItem.setAmount(heldItem.getAmount() - 1);


            if (getLives(killedPlayer) == 1) {
                setLives(killedPlayer, -1);
                if (killedPlayer.isOnline()) {
                    ((Player) killedPlayer).getInventory().clear();
                    ((Player) killedPlayer).kickPlayer("§c§lYou have no more lives!");
                }
                Bukkit.broadcastMessage("§c§l" + killedPlayer.getName() + " has been §r§4§lELIMINATED");
                return;
        }

        removeLives(killedPlayer, 1);
        p.sendMessage("§a§lRemoved 1 life from " + killedPlayer.getName() + "!");

    }

}
