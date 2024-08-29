package me.minignomer.shadowsmp;

import com.samjakob.spigui.SpiGUI;
import me.minignomer.shadowsmp.abilities.banshee.ApplyWeaknessOnKill;
import me.minignomer.shadowsmp.abilities.general.*;
import me.minignomer.shadowsmp.abilities.jinn.JinnRemoveHearts;
import me.minignomer.shadowsmp.abilities.mimic.MimicGhosts;
import me.minignomer.shadowsmp.abilities.myling.GiveInvis;
import me.minignomer.shadowsmp.abilities.obake.ObakeItem;
import me.minignomer.shadowsmp.abilities.oni.OniItem;
import me.minignomer.shadowsmp.abilities.oni.PreventPearls;
import me.minignomer.shadowsmp.abilities.onryo.DimensionEffects;
import me.minignomer.shadowsmp.abilities.phantom.PhantomDebuff;
import me.minignomer.shadowsmp.abilities.wraith.WraithVegetarian;
import me.minignomer.shadowsmp.actionbardisplay.DisplayActionBar;
import me.minignomer.shadowsmp.commands.GetCommand;
import me.minignomer.shadowsmp.commands.GetTabCompleter;
import me.minignomer.shadowsmp.config.ghosttype.AssignGhost;
import me.minignomer.shadowsmp.config.lives.LivesListener;
import me.minignomer.shadowsmp.items.CraftingRecipes;
import me.minignomer.shadowsmp.items.ItemManager;
import me.minignomer.shadowsmp.items.huntorb.HuntOrb;
import me.minignomer.shadowsmp.items.preventitemdrop.PreventItemDrop;
import me.minignomer.shadowsmp.items.reroll.ReRoll;
import me.minignomer.shadowsmp.items.revive.Revive;
import me.minignomer.shadowsmp.items.smudgestick.SmudgeStick;
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
        getServer().getPluginManager().registerEvents(new HuntOrb(), this);
        getServer().getPluginManager().registerEvents(new ApplyPassiveEffects(), this);
        getServer().getPluginManager().registerEvents(new PhantomDebuff(), this);
        getServer().getPluginManager().registerEvents(new WraithVegetarian(), this);
        getServer().getPluginManager().registerEvents(new ModifyAttackDamage(), this);
        getServer().getPluginManager().registerEvents(new OniItem(), this);
        getServer().getPluginManager().registerEvents(new PreventItemDrop(), this);
        getServer().getPluginManager().registerEvents(new ReRoll(), this);
        getServer().getPluginManager().registerEvents(new PreventPearls(), this);
        getServer().getPluginManager().registerEvents(new ApplyWeaknessOnKill(), this);
        getServer().getPluginManager().registerEvents(new DimensionEffects(), this);
        getServer().getPluginManager().registerEvents(new TimeEffects(), this);
        getServer().getPluginManager().registerEvents(new SpreadEffects(), this);
        getServer().getPluginManager().registerEvents(new SmudgeStick(), this);
        getServer().getPluginManager().registerEvents(new MimicGhosts(), this);
        getServer().getPluginManager().registerEvents(new GiveInvis(), this);
        getServer().getPluginManager().registerEvents(new ModifyDefenceDamage(), this);
        getServer().getPluginManager().registerEvents(new JinnRemoveHearts(), this);
        getServer().getPluginManager().registerEvents(new ObakeItem(), this);
        getServer().getPluginManager().registerEvents(new CraftingRecipes(), this);
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
