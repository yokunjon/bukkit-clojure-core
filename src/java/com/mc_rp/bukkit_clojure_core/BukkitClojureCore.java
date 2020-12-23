package com.mc_rp.bukkit_clojure_core;

import com.mc_rp.bukkit_clojure_core.manager.Manager;
import java.nio.file.Path;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitClojureCore extends JavaPlugin {
  private final Manager manager = new Manager();
  private final Path dataFolder = getInstance().getDataFolder().getAbsoluteFile().toPath();
  @Override
  public void onEnable() {
    manager.initialize();
    Bukkit.getLogger().info("BukkitClojureCore enabled.");
  }
  @Override
  public void onDisable() {
    Bukkit.getLogger().info("BukkitClojureCore disabled.");
  }
  public static BukkitClojureCore getInstance() {
    return JavaPlugin.getPlugin(BukkitClojureCore.class);
  }
  public Manager getManager() {
    return manager;
  }
  public Path getDataPath() {
    return dataFolder;
  }
}
