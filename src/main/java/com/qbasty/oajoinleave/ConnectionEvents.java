package com.qbasty.oajoinleave;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionEvents implements Listener {
    private final Main plugin;

    public ConnectionEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (plugin.getConfig().getBoolean("data." + player.getUniqueId(), true)) {
                try {
                    String joinMsg = plugin.getConfig().getString("messages.join-message").replace("%player%", e.getPlayer().getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinMsg));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (plugin.getConfig().getBoolean("data." + player.getUniqueId(), true)) {
                String quitMsg = plugin.getConfig().getString("messages.quit-message").replace("%player%", e.getPlayer().getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', quitMsg));
            }
        }
    }
}