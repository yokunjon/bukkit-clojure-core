package com.mc_rp.bukkit_clojure.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class Container implements Loadable {
  private final HashSet<Path> containers = new HashSet<>();
  private final HashSet<Path> scripts = new HashSet<>();
  private final Manager manager;
  private final Path path;

  Container(Manager manager, Path path) {
    this.manager = manager;
    this.path = path;
  }
  public void load() {
    try {
      Files.walk(path).forEach(this::loadSingle);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void unload() {
    containers.forEach(this::unloadSingle);
    scripts.forEach(this::unloadSingle);
  }
  public void loadSingle(Path path) {
    Loadable loadable;
    if (Files.isDirectory(path)) {
      loadable = new Container(manager, path);
      manager.getContainersRef().put(path, loadable);
      containers.add(path);
    } else {
      loadable = new Script(manager, path);
      manager.getScriptsRef().put(path, loadable);
      scripts.add(path);
    }
    loadable.load();
  }
  public void unloadSingle(Path path) {
    Loadable loadable;
    if (containers.contains(path)) {
      loadable = manager.getContainersRef().get(path);
      manager.getContainersRef().remove(path);
      containers.remove(path);
    } else {
      loadable = manager.getScriptsRef().get(path);
      manager.getScriptsRef().remove(path);
      scripts.remove(path);
    }
    loadable.unload();
  }
}
