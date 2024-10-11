package org.skyzzin;


import org.bukkit.plugin.java.JavaPlugin;
import org.skyzzin.generators.RegisterGenerators;


public final class mx_machinario extends JavaPlugin {

    @Override
    public void onEnable() {

       new RegisterGenerators(this);
       //new RegisterRoutines(this);

    }

    @Override
    public void onDisable() {

    }
}
