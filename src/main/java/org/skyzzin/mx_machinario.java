package org.skyzzin;


import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.skyzzin.commands.RegisterCommands;
import org.skyzzin.generators.RegisterGenerators;


public final class mx_machinario extends JavaPlugin {

    @Override
    public void onEnable() {

        

        saveDefaultConfig();
        new RegisterGenerators(this);
        new RegisterCommands(this);


    }

    @Override
    public void onDisable() {

    }
}
