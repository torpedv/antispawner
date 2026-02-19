package ru.antispawner;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private final AntiSpawnerPlugin plugin;

    public ReloadCommand(AntiSpawnerPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("antispawner.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigManager().getNoPermReloadMessage()));
            return true;
        }

        plugin.reloadPluginConfig();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfigManager().getReloadMessage()));
        return true;
    }
}
