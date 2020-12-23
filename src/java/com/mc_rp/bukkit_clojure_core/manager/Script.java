package com.mc_rp.bukkit_clojure_core.manager;

import clojure.lang.Compiler;
import clojure.lang.Var;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

class Script implements Loadable {
  private final Path path;
  private final Manager manager;
  Script(Manager manager, Path path) {
    this.manager = manager;
    this.path = path;
  }
  public void load() {
    try {
      FileReader reader = new FileReader(path.toString());
      ClassLoader classLoader = manager.newClassLoader();
      Thread.currentThread().setContextClassLoader(classLoader);
      Compiler.load(reader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  public void unload() {
    Thread.currentThread().setContextClassLoader(manager.getClassLoader());
    Var.popThreadBindings();
  }
}
