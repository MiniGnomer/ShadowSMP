package me.minignomer.shadowsmp.items.preventitemdrop;

import me.minignomer.shadowsmp.config.ghosttype.GhostManager;
import me.minignomer.shadowsmp.config.ghosttype.GhostType;
import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class PreventItemDrop extends GhostManager implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        List<ItemStack> drops = e.getDrops();
        drops.remove(ItemManager.oniItem);
        drops.remove(ItemManager.obakeItem);
    }
    @EventHandler
    public void onSignDyeEvent(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!(e.getClickedBlock().getState() instanceof Sign)) return;

        ItemStack mainHandItem = e.getPlayer().getInventory().getItemInMainHand();
        ItemStack offHandItem = e.getPlayer().getInventory().getItemInOffHand();

        if (mainHandItem.isSimilar(ItemManager.oniItem) || mainHandItem.isSimilar(ItemManager.obakeItem) || mainHandItem.isSimilar(ItemManager.reRoll) ||
            offHandItem.isSimilar(ItemManager.oniItem) || offHandItem.isSimilar(ItemManager.obakeItem) || offHandItem.isSimilar(ItemManager.reRoll)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onItemFrameClickEvent(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.ITEM_FRAME && e.getRightClicked().getType() != EntityType.GLOW_ITEM_FRAME) return;

        ItemStack mainHandItem = e.getPlayer().getInventory().getItemInMainHand();
        ItemStack offHandItem = e.getPlayer().getInventory().getItemInOffHand();

        if (mainHandItem.isSimilar(ItemManager.oniItem) || mainHandItem.isSimilar(ItemManager.obakeItem) ||
            offHandItem.isSimilar(ItemManager.oniItem) || offHandItem.isSimilar(ItemManager.obakeItem)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onSheepDyeEvent(SheepDyeWoolEvent e) {
        PlayerInventory inventory = e.getPlayer().getInventory();

        if (inventory.getItemInMainHand().isSimilar(ItemManager.oniItem)
                || inventory.getItemInOffHand().isSimilar(ItemManager.oniItem)) {
            e.setCancelled(true);
            return;
        }

        if (inventory.getItemInMainHand().isSimilar(ItemManager.obakeItem)
                || inventory.getItemInOffHand().isSimilar(ItemManager.obakeItem)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (hasGhost(p, GhostType.ONI)) {
            p.getInventory().addItem(ItemManager.oniItem);
        }
        if (hasGhost(p, GhostType.OBAKE)) {
            p.getInventory().addItem(ItemManager.obakeItem);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory clickedInventory = e.getClickedInventory();

        // Check if player shift clicks the item
        if (e.getClick().isShiftClick()) {

            // Make sure the player is shift clicking from their own inventory
            if (clickedInventory != e.getWhoClicked().getInventory()) return;

            ItemStack clickedItem = e.getCurrentItem();

            // Make sure the item is an element
            if (clickedItem == null) return;
            if (!clickedItem.isSimilar(ItemManager.oniItem) && !clickedItem.isSimilar(ItemManager.obakeItem)) return;

            e.setCancelled(true);

        }

        // Check if player is hotkeying the item
        else if (e.getClick().isKeyboardClick()) {


            // Check if the cursor is above another inventory
            if (clickedInventory != e.getWhoClicked().getInventory()) {

                // Check if they're doing the offhand item
                if (e.getClick() == ClickType.SWAP_OFFHAND) {

                    ItemStack offHandItem = e.getWhoClicked().getInventory().getItemInOffHand();

                    if (offHandItem.isSimilar(ItemManager.oniItem) || offHandItem.isSimilar(ItemManager.obakeItem)) {
                        e.setCancelled(true);
                    }
                    return;
                }

                // Get hotkeyed item
                if (e.getHotbarButton() < 0) return;
                ItemStack hotkeyedItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());

                if (hotkeyedItem != null) {
                    if (hotkeyedItem.isSimilar(ItemManager.oniItem) || hotkeyedItem.isSimilar(ItemManager.obakeItem)) {
                        e.setCancelled(true);
                    }
                }
            }
        }


        else {
            if (clickedInventory == e.getWhoClicked().getInventory()) return;

            ItemStack onCursor = e.getCursor();

            if (onCursor == null) return;
            if (!onCursor.isSimilar(ItemManager.oniItem) && !onCursor.isSimilar(ItemManager.obakeItem)) return;

            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {

        ItemStack draggedItem = e.getOldCursor();

        if (draggedItem.isSimilar(ItemManager.oniItem)
            || draggedItem.isSimilar(ItemManager.obakeItem)) {
            int inventorySize = e.getInventory().getSize();

            for (int i : e.getRawSlots()) {
                if (i < inventorySize) {
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().isSimilar(ItemManager.oniItem)
            || e.getItemDrop().getItemStack().isSimilar(ItemManager.obakeItem)) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                p.getInventory().setItemInOffHand(e.getItemDrop().getItemStack());
            }
        }
    }

    @EventHandler
    public void onItemMoveInventories(InventoryMoveItemEvent e) {
        if (e.getItem().isSimilar(ItemManager.oniItem)
             || e.getItem().isSimilar(ItemManager.obakeItem)) {
            e.setCancelled(true);
        }
    }
}
