package com.mc_rp.bukkit_clojure_core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitClojureCore extends JavaPlugin {
  @Override
  public void onEnable() {
    Bukkit.getLogger().info("BukkitClojureCore enabled.");
  }
  @Override
  public void onDisable() {
    Bukkit.getLogger().info("BukkitClojureCore disabled.");
  }
}
