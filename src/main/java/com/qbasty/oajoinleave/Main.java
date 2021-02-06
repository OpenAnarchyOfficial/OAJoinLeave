package com.qbasty.oajoinleave;

import com.google.gson.Gson;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    private final PluginManager pluginManager = getServer().getPluginManager();
    public static File dataFolder;
    public static Gson gson = new Gson();
    public static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        dataFolder = this.getDataFolder();

        pluginManager.registerEvents(new ConnectionEvents(this), this);
        getCommand("toggleconnectionmsgs").setExecutor(new JoinLeave(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}