package com.mc_rp.bukkit_clojure_core.manager;

import clojure.lang.Compiler;
import clojure.lang.DynamicClassLoader;
import clojure.lang.RT;
import clojure.lang.Var;
import com.mc_rp.bukkit_clojure_core.BukkitClojureCore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Manager {

  private ClassLoader classLoader;
  private final Path scriptsPath;
  private final Container container;
  private final HashMap<Path, Loadable> containersRef = new HashMap<>();
  private final HashMap<Path, Loadable> scriptsRef = new HashMap<>();

  public Manager(BukkitClojureCore plugin) {
    scriptsPath = plugin.getDataPath().resolve("scripts");
    container = new Container(this, scriptsPath);
  }

  public void initialize() {
    ClassLoader current = Thread.currentThread().getContextClassLoader();
    ClassLoader bukkit = BukkitClojureCore.class.getClassLoader();
    Thread.currentThread().setContextClassLoader(bukkit);
    RT.init();
    classLoader = newClassLoader(bukkit);
    try {
      RT.load("clojure/core");
      RT.load("bukkit_clojure_core");
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    Thread.currentThread().setContextClassLoader(current);
    if (Files.exists(scriptsPath)) return;
    try {
      Files.createDirectories(scriptsPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public ClassLoader getClassLoader() {
    return classLoader;
  }
  public void loadAll() {
    container.load();
  }
  ClassLoader newClassLoader() {
    return newClassLoader(classLoader);
  }
  ClassLoader newClassLoader(ClassLoader classLoader) {
    ClassLoader newClassLoader = new DynamicClassLoader(classLoader);
    Var.pushThreadBindings(RT.map(Compiler.LOADER, newClassLoader));
    return newClassLoader;
  }
  HashMap<Path, Loadable> getContainersRef() {
    return containersRef;
  }
  HashMap<Path, Loadable> getScriptsRef() {
    return scriptsRef;
  }
}
