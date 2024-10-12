package org.skyzzin.commands;

import org.bukkit.plugin.java.JavaPlugin;

public class RegisterCommands {
    public RegisterCommands(JavaPlugin plugin)
    {
        plugin.getCommand("cleargenerators").setExecutor(new ClearGenerators());
        plugin.getCommand("cg").setExecutor(new ClearGenerators());
    }
}
