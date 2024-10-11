package org.skyzzin.generators;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.skyzzin.util.GeneratorConfig;
import org.skyzzin.util.YMLReader;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Generator implements Listener {
    private final Set<GeneratorInstance> generators = new HashSet<>();
    private final JavaPlugin plugin;

    public Generator(JavaPlugin _plugin) {
        this.plugin = _plugin;
    }

    @EventHandler
    public void spawnGenerator(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.SPAWNER) {
                Map<String, GeneratorConfig> generatorsConfig = YMLReader.loadGenerators("config.yml");

                for(Map.Entry<String, GeneratorConfig> entry : generatorsConfig.entrySet())
                {

                    Material material = Material.valueOf(entry.getValue().getType().toUpperCase());

                    if (event.getPlayer().getInventory().getItemInMainHand().getType() == material) {
                        Location location = getTopOfBlockLocation(event.getClickedBlock().getLocation());

                        for (GeneratorInstance generator : generators) {
                            if (generator.isAtLocation(location)) {

                                event.getPlayer().sendMessage(ChatColor.RED + "Já tem um gerador nesse local.");


                                return;
                            }
                        }
                        generators.add(new GeneratorInstance(location, plugin,material));
                    }
                }
            }
        }
    }

    @EventHandler
    public void destroyGenerator(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.SPAWNER) {
                Location location = getTopOfBlockLocation(event.getClickedBlock().getLocation());

                // Procura por um gerador ativo nessa localização e o remove
                generators.removeIf(generatorInstance -> {
                    boolean isSameLocation = generatorInstance.isAtLocation(location);
                    if (isSameLocation) {
                        generatorInstance.removeItem(); // Remove o item gerado
                    }
                    return isSameLocation; // Remove da lista de geradores
                });
            }
        }
    }

    @EventHandler void openGenerator(PlayerInteractEvent event){
        if(event.getClickedBlock().getType() == Material.SPAWNER)
        {
            Location location = getTopOfBlockLocation(event.getClickedBlock().getLocation());

            for (GeneratorInstance generator : generators) {
                if (generator.isAtLocation(location)) {

                    //event.getPlayer().openInventory(generator.getInventory());


                    return;
                }
            }


        }
    }

    private Location getTopOfBlockLocation(Location location) {
        return new Location(location.getWorld(), location.getBlockX() + 0.5, location.getBlockY() + 1, location.getBlockZ() + 0.5);
    }
}
