package me.minignomer.shadowsmp;

import com.samjakob.spigui.SpiGUI;
import me.minignomer.shadowsmp.abilities.phantom.PhantomBuff;
import me.minignomer.shadowsmp.actionbardisplay.DisplayActionBar;
import me.minignomer.shadowsmp.commands.GetCommand;
import me.minignomer.shadowsmp.commands.GetTabCompleter;
import me.minignomer.shadowsmp.config.ghosttype.AssignGhost;
import me.minignomer.shadowsmp.config.lives.LivesListener;
import me.minignomer.shadowsmp.items.ItemManager;
import me.minignomer.shadowsmp.items.revive.Revive;
import me.minignomer.shadowsmp.items.soulshard.SoulShardListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShadowSMP extends JavaPlugin {

    public static ShadowSMP plugin;

    public static SpiGUI spiGUI;

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new AssignGhost(), this);
        getServer().getPluginManager().registerEvents(new LivesListener(), this);
        getServer().getPluginManager().registerEvents(new DisplayActionBar(), this);
        getServer().getPluginManager().registerEvents(new SoulShardListener(), this);
        getServer().getPluginManager().registerEvents(new Revive(), this);

        // Buffs
        getServer().getPluginManager().registerEvents(new PhantomBuff(), this);

        // Debuffs
    }

    private void registerCommands() {
        getCommand("get").setExecutor(new GetCommand());
        getCommand("get").setTabCompleter(new GetTabCompleter());
    }

    @Override
    public void onEnable() {
        plugin = this;
        spiGUI = new SpiGUI(this);

        ItemManager.registerItems();
        registerListeners();
        registerCommands();

    }
}
