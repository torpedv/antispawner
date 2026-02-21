package ru.antispawner;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public class AntiSpawnerPlugin extends JavaPlugin {
    private static AntiSpawnerPlugin instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // 
        getServer().getPluginManager().registerEvents(new SpawnerBreakListener(this), this);

        // посиси хуйца
        getCommand("asreload").setExecutor(new ReloadCommand(this));

        getLogger().info("AntiSpawner включён. Версия " + getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        getLogger().info("AntiSpawner выключен.");
    }

    public static AntiSpawnerPlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void reloadPluginConfig() {
        configManager.reloadConfig();
    }
}
