package me.minignomer.shadowsmp.config.ghosttype;

import me.minignomer.shadowsmp.config.ConfigManager;
import me.minignomer.shadowsmp.items.ItemManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class GhostManager extends ConfigManager {


    public static GhostType getGhost(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();

        return GhostType.valueOf(getConfig().getString("Type." + uuid));
    }
    public static boolean hasGhost(Player p, GhostType ghostType) {
        UUID uuid = p.getUniqueId();

        return GhostType.valueOf(getConfig().getString("Type." + uuid)) == ghostType;
    }

    public static void setGhost(OfflinePlayer p, GhostType type) {
        UUID uuid = p.getUniqueId();

        setConfig("Type." + uuid, type.toString());
    }

    public static void addMimic(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        List<String> list = getConfig().getStringList("Mimics");
        list.add(uuid.toString());
        setConfig("Mimics", list);
    }

    public static void removeMimic(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        List<String> list = getConfig().getStringList("Mimics");
        list.remove(uuid.toString());
        setConfig("Mimics", list);
    }
    public static boolean isMimic(Player p) {
        UUID uuid = p.getUniqueId();
        return getConfig().getStringList("Mimics").contains(uuid.toString());
    }

    private static GhostType randomizeGhost(GhostType currentGhost) {
        Random rand = new Random();
        int randInt = rand.nextInt(1, 24 + 1);

        while (currentGhost == GhostType.values()[randInt - 1])
        {
            randInt = rand.nextInt(1, 24 + 1);
        }

        GhostType newGhost = GhostType.values()[randInt - 1];

        newGhost = reRandomizeRareGhosts(currentGhost, newGhost);

        return newGhost;
    }

    private static GhostType randomizeGhost() {
        Random rand = new Random();
        int randInt = rand.nextInt(1, 24 + 1);

        GhostType newGhost = GhostType.values()[randInt - 1];

        newGhost = reRandomizeRareGhosts(newGhost);

        return newGhost;
    }

    public static HashMap<PotionEffectType, Integer> getPositiveGhostEffects(OfflinePlayer p) {

        HashMap<PotionEffectType, Integer> effects = new HashMap<>();

        switch (getGhost(p)) {
            case PHANTOM:
            case ONI:
            case SHADE:
                effects.put(PotionEffectType.INVISIBILITY, 0);
                break;
            case SPIRIT:
            case WRAITH:
                effects.put(PotionEffectType.INCREASE_DAMAGE, 0);
                break;
            case DEOGEN:
                effects.put(PotionEffectType.SPEED, 2);
                break;
            case DEMON:
                effects.put(PotionEffectType.INCREASE_DAMAGE, 2);
                break;
            case JINN:
                effects.put(PotionEffectType.INCREASE_DAMAGE, 1);
                effects.put(PotionEffectType.SPEED, 0);
                break;
            case TWINS:
                effects.put(PotionEffectType.FAST_DIGGING, 1);
                break;
            case RAIJU:
                effects.put(PotionEffectType.FAST_DIGGING, 0);
                break;
            case THAYE:
                effects.put(PotionEffectType.DAMAGE_RESISTANCE, 1);
                break;
            case MOROI:
                effects.put(PotionEffectType.SPEED, 1);
                break;
            default:
                return null;
        }

        return effects;
    }

    public static HashMap<PotionEffectType, Integer> getNegativeGhostEffects(OfflinePlayer p) {

        HashMap<PotionEffectType, Integer> effects = new HashMap<>();

        switch (getGhost(p)) {

            case SPIRIT:
                effects.put(PotionEffectType.SLOW, 1);
                break;
            case GORYO:
            case SHADE:
                effects.put(PotionEffectType.SLOW, 0);
                break;
            case REVENANT:
                effects.put(PotionEffectType.DARKNESS, 0);
                break;
            case TWINS:
            case THAYE:
            case MOROI:
                effects.put(PotionEffectType.WEAKNESS, 0);
                break;
            case RAIJU:
                effects.put(PotionEffectType.GLOWING, 0);
                break;
            default:
                return null;
        }

        return effects;
    }

    private static GhostType reRandomizeRareGhosts(GhostType ghost) {
        Random rand = new Random();

        if (ghost == GhostType.DEMON)
        {
            int randInt = rand.nextInt(1, 4 + 1);

            if (randInt != 1)
            {
                return randomizeGhost();
            }

            return ghost;
        }

        if (ghost == GhostType.JINN || ghost == GhostType.MIMIC)
        {
            int randInt = rand.nextInt(1, 2 + 1);

            if (randInt != 1)
            {
                return randomizeGhost();
            }


            return ghost;
        }

        return ghost;
    }

    private static GhostType reRandomizeRareGhosts(GhostType oldGhost, GhostType ghost) {
        Random rand = new Random();

        if (ghost == GhostType.DEMON)
        {
            int randInt = rand.nextInt(1, 4 + 1);

            if (randInt != 1)
            {
                return randomizeGhost(oldGhost);
            }

            return ghost;
        }

        if (ghost == GhostType.JINN ||
            ghost == GhostType.MIMIC ||
            ghost == GhostType.REVENANT ||
            ghost == GhostType.MYLING ||
            ghost == GhostType.YUERI ||
            ghost == GhostType.ONRYO)
        {
            int randInt = rand.nextInt(1, 2 + 1);

            if (randInt != 1)
            {
                return randomizeGhost(oldGhost);
            }

            return ghost;
        }

        return ghost;
    }

    public static void assignRandomGhost(Player p) {
        try
        {
            GhostType currentGhost = getGhost(p);
            GhostType newGhost = randomizeGhost(currentGhost);

            p.getInventory().remove(ItemManager.oniItem);
            p.getInventory().remove(ItemManager.obakeItem);

            if (p.getInventory().getItemInOffHand().isSimilar(ItemManager.oniItem))
                p.getInventory().getItemInOffHand().setAmount(0);

            else if (p.getInventory().getItemInOffHand().isSimilar(ItemManager.obakeItem))
                p.getInventory().getItemInOffHand().setAmount(0);

            if (isMimic(p))
                removeMimic(p);

            switch (newGhost) {
                case ONI:
                    if (p.getInventory().firstEmpty() == -1) {
                        p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                        p.getInventory().setItemInOffHand(ItemManager.oniItem);
                    } else {
                        p.getInventory().addItem(ItemManager.oniItem);
                    }
                    break;
                case OBAKE:
                    if (p.getInventory().firstEmpty() == -1) {
                        p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                        p.getInventory().setItemInOffHand(ItemManager.obakeItem);
                    } else {
                        p.getInventory().addItem(ItemManager.obakeItem);
                    }
                    break;
                case MIMIC:
                    addMimic(p);
                    break;
            }

            setGhost(p, newGhost);

        }

        catch (NullPointerException err)
        {
            GhostType newGhost = randomizeGhost();

            switch (newGhost) {
                case ONI:
                    if (p.getInventory().firstEmpty() == -1) {
                        p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                        p.getInventory().setItemInOffHand(ItemManager.oniItem);
                    }
                    else {
                        p.getInventory().addItem(ItemManager.oniItem);
                    }
                    break;
                case OBAKE:
                    if (p.getInventory().firstEmpty() == -1) {
                        p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                        p.getInventory().setItemInOffHand(ItemManager.obakeItem);
                    }
                    else {
                        p.getInventory().addItem(ItemManager.obakeItem);
                    }
                    break;
                case MIMIC:
                    addMimic(p);
                    break;
            }

            setGhost(p, newGhost);
        }
    }

    public static void assignGhost(Player p, GhostType newGhost, boolean keepMimic) {

        try
        {
            GhostType currentGhost = getGhost(p);

            p.getInventory().remove(ItemManager.oniItem);
            p.getInventory().remove(ItemManager.obakeItem);

            if (currentGhost == GhostType.MIMIC && !keepMimic) {
                removeMimic(p);
            }
        }

        catch (NullPointerException ignore) {}

        if (newGhost == GhostType.ONI) {
            p.getInventory().addItem(ItemManager.oniItem);
        }

        else if (newGhost == GhostType.OBAKE) {
            p.getInventory().addItem(ItemManager.obakeItem);
        }

        else if (newGhost == GhostType.MIMIC && !keepMimic) {
            addMimic(p);
        }

        setGhost(p, newGhost);
    }
}