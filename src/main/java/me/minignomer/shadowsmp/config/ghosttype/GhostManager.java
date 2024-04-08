package me.minignomer.shadowsmp.config.ghosttype;

import me.minignomer.shadowsmp.config.ConfigManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.UUID;

public class GhostManager extends ConfigManager {

    public static GhostType getGhost(Player p) {
        UUID uuid = p.getUniqueId();

        return GhostType.valueOf(getConfig().getString("Type." + uuid));
    }

    public static GhostType getGhost(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();

        return GhostType.valueOf(getConfig().getString("Type." + uuid));
    }

    public static void setGhost(Player p, GhostType type) {
        UUID uuid = p.getUniqueId();

        setConfig("Type." + uuid, type.toString());
    }

    public static void setGhost(OfflinePlayer p, GhostType type) {
        UUID uuid = p.getUniqueId();

        setConfig("Type." + uuid, type.toString());
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

        if (ghost == GhostType.JINN || ghost == GhostType.MIMIC)
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

            setGhost(p, newGhost);

        }

        catch (NullPointerException exception)
        {
            setGhost(p, randomizeGhost());
        }
    }
}