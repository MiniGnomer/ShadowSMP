package me.minignomer.shadowsmp.config;

import me.minignomer.shadowsmp.ShadowSMP;
import org.bukkit.configuration.Configuration;

public class ConfigManager {
    public static Configuration getConfig() {
        return getPlugin().getConfig();
    }

    public static ShadowSMP getPlugin() {
        return ShadowSMP.plugin;
    }

    public static void setConfig(String path, Object value) {
        getConfig().set(path, value);
        updateConfig();
    }

    public static void updateConfig() {
        getPlugin().saveConfig();
    }
}
