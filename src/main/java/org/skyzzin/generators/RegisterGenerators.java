package org.skyzzin.generators;

import org.bukkit.plugin.java.JavaPlugin;

public class RegisterGenerators {
    public RegisterGenerators(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new Generator(plugin) ,  plugin);
    }
}
