(defproject bukkit-clojure-core "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories [["https://papermc.io/repo/repository/maven-public/"]]
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :profiles {:provided {:dependencies [[com.destroystokyo.paper/paper-api "1.16.4-R0.1-SNAPSHOT"]]}}
  :repl-options {:init-ns bukkit-clojure-core.core}
  :source-paths ["src/clojure"]
  :aot :all
  :java-source-paths ["src/java"])
