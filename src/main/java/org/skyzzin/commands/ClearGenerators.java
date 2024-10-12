package org.skyzzin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

public class ClearGenerators implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        for(Entity entity : Bukkit.getWorlds().get(0).getEntities())
        {
            if(entity instanceof ArmorStand)
            {
                entity.remove();
            }
        }
        commandSender.sendMessage(ChatColor.GREEN + "Clear All Generators");
        return false;
    }
}
