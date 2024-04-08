package me.minignomer.shadowsmp.config.lives;

import me.minignomer.shadowsmp.config.ConfigManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LivesManager extends ConfigManager {

    public static int getLives(Player p) {
        UUID uuid = p.getUniqueId();

        return getConfig().getInt("Lives." + uuid);
    }

    public static int getLives(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();

        return getConfig().getInt("Lives." + uuid);
    }

    public static void setLives(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        setConfig("Lives." + uuid, amount);
    }

    public static void setLives(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();

        setConfig("Lives." + uuid, amount);
    }

    public static void addLives(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        setConfig("Lives." + uuid, getLives(p) + amount);
    }

    public static void addLives(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();

        setConfig("Lives." + uuid, getLives(p) + amount);
    }

    public static void removeLives(Player p, int amount) {
        UUID uuid = p.getUniqueId();

        setConfig("Lives." + uuid, getLives(p) - amount);
    }

    public static void removeLives(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();

        setConfig("Lives." + uuid, getLives(p) - amount);
    }
}