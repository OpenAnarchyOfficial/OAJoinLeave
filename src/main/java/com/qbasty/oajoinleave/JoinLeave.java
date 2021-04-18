package com.qbasty.oajoinleave;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinLeave implements CommandExecutor {
    private final Main plugin;

    public JoinLeave(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (plugin.getConfig().getBoolean("toggle-connection-msgs-cmd")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("The console can not toggle connection messages.");
                return false;
            } else {
                try {
                    Player p = (Player) sender;
                    String id = p.getUniqueId().toString();
                    if (plugin.getConfig().getBoolean("data." + id, true)) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.connection-cmd-off")));
                        plugin.getConfig().set("data." + id, false);
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.connection-cmd-on")));
                        plugin.getConfig().set("data." + id, true);
                    }
                    plugin.saveConfig();
                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
}