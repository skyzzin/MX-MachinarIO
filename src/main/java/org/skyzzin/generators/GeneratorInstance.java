package org.skyzzin.generators;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.skyzzin.util.GeneratorConfig;
import org.skyzzin.util.YMLReader;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GeneratorInstance implements Listener {
    private final Set<UUID> itensNotPickup = new HashSet<>();
    private final JavaPlugin plugin;
    private final Location location;
    private Item generatedItem;
    public boolean active = false;
    private Material material;
    public Inventory inventory;

    public GeneratorInstance(Location loc, JavaPlugin _plugin, Material material) {
        this.plugin = _plugin;
        this.location = loc;
        this.material = material;

       // this.inventory = Bukkit.createInventory(null, InventoryType.HOPPER,entryConfig.getTitle());

        if (!active) {
            startGenerator();

            active = true;
        }

        plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    public void startGenerator() {
        Map<String, GeneratorConfig> generatorsConfig = YMLReader.loadGenerators("config.yml");

            ItemStack itemStack = new ItemStack(material);
            ItemMeta meta = itemStack.getItemMeta();
            if (meta != null) {
                meta.setDisplayName("Item Único " + UUID.randomUUID());
                itemStack.setItemMeta(meta);
            }

            generatedItem = location.getWorld().dropItem(location, itemStack);
            generatedItem.setVelocity(generatedItem.getVelocity().zero());

            itensNotPickup.add(generatedItem.getUniqueId());


    }

    public void removeItem() {
        if (generatedItem != null && !generatedItem.isDead()) {
            generatedItem.remove();
            itensNotPickup.remove(generatedItem.getUniqueId());
            active = false;
        }
    }

    public boolean isAtLocation(Location loc) {
        return location != null && location.equals(loc);
    }

    public Inventory openInventory()
    {
        return this.inventory;
    }



    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (itensNotPickup.contains(event.getItem().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
