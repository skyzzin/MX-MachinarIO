package org.skyzzin.rotines;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SendMessagePlayer implements Listener {
    JavaPlugin plugin;
    public SendMessagePlayer(JavaPlugin _plugin)
    {
        plugin = _plugin;
    }
    @EventHandler
    public void onPlayerJoinEnvent(PlayerJoinEvent event){
        new BukkitRunnable(){
            @Override
            public void run()
            {
                if(!event.getPlayer().isOnline())
                {
                    Bukkit.getLogger().info("o Player Saiu Então Vamo Parar de Viadagem e Para de Manda Menssagem Pra ele");
                    cancel();
                    return;
                }
                event.getPlayer().sendMessage("Olá Player");
            }
        }.runTaskTimer(plugin,0L,20);
    }
}
