package org.skyzzin.rotines;

import org.bukkit.plugin.java.JavaPlugin;

public class RegisterRoutines {
    public RegisterRoutines(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new SendMessagePlayer(plugin),plugin);
    }
}
