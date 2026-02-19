package ru.antispawner;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public class ConfigManager {
    private final JavaPlugin plugin;
    private String permissionNode;
    private double chance;
    private String noPermMessage;
    private String failedChanceMessage;
    private String reloadMessage;
    private String noPermReloadMessage;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        permissionNode = config.getString("permission-node", "antispawner.break");
        chance = config.getDouble("chance", 0.5);
        if (chance < 0.0) chance = 0.0;
        if (chance > 1.0) chance = 1.0;

        noPermMessage = config.getString("messages.no-permission", "&cУ вас нет прав, чтобы сломать спавнер!");
        failedChanceMessage = config.getString("messages.failed-chance", "&cСпавнер устоял! Ваша удача подвела вас.");
        reloadMessage = config.getString("messages.reload", "&aКонфиг перезагружен.");
        noPermReloadMessage = config.getString("messages.no-permission-reload", "&cУ вас нет прав на перезагрузку.");
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        loadConfig(); // перезагружаем значения
    }

    public String getPermissionNode() { return permissionNode; }
    public double getChance() { return chance; }
    public String getNoPermMessage() { return noPermMessage; }
    public String getFailedChanceMessage() { return failedChanceMessage; }
    public String getReloadMessage() { return reloadMessage; }
    public String getNoPermReloadMessage() { return noPermReloadMessage; }
}
