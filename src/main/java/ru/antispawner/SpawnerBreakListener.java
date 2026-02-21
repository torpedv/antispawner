package ru.antispawner;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.Random;

public class SpawnerBreakListener implements Listener {
    private final AntiSpawnerPlugin plugin;
    private final Random random = new Random();

    public SpawnerBreakListener(AntiSpawnerPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // 
        if (event.getBlock().getType() != Material.SPAWNER) {
            return;
        }

        Player player = event.getPlayer();
        ConfigManager config = plugin.getConfigManager();

        // 
        if (!player.hasPermission(config.getPermissionNode())) {
            event.setCancelled(true);
            player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', config.getNoPermMessage()));
            return;
        }

        // 
        double chance = config.getChance();
        if (random.nextDouble() >= chance) { // не повезло
            event.setCancelled(true);
            player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', config.getFailedChanceMessage()));
        }
        // 
    }
}
