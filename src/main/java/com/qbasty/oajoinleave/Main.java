package com.qbasty.oajoinleave;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ConnectionEvents(this), this);
        getCommand("toggleconnectionmsgs").setExecutor(new JoinLeave(this));
    }
}