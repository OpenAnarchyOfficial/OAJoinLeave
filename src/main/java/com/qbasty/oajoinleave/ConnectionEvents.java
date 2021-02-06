package com.qbasty.oajoinleave;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionEvents implements Listener {

    Main plugin;

    public ConnectionEvents(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        for(Player player : Bukkit.getOnlinePlayers()){
            JoinLeave.toggled.putIfAbsent(player.getUniqueId().toString(), true);
            if(JoinLeave.toggled.get(player.getUniqueId().toString())){
                try {
                    String joinMsg = plugin.getConfig().getString("messages.join-message").replace("%player%", p.getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinMsg));
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        for (Player player : Bukkit.getOnlinePlayers()){
            if (JoinLeave.toggled.get(player.getUniqueId().toString())){
                String quitMsg = plugin.getConfig().getString("messages.quit-message").replace("%player%", e.getPlayer().getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', quitMsg));
            }
        }
    }
}