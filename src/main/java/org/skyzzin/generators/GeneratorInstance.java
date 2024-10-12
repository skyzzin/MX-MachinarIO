package org.skyzzin.generators;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
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
    public boolean isFuel = false;

    public ArmorStand armorStand;

    public GeneratorInstance( Location loc, JavaPlugin _plugin, Material material,String title) {
        this.plugin = _plugin;
        this.location = loc;
        this.material = material;

        this.inventory = Bukkit.createInventory(null, InventoryType.HOPPER,title);

        if (!active) {
            startGenerator();

            active = true;
            armorStand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomName(ChatColor.RED +"NoFuel");
            armorStand.setCustomNameVisible(true);
        }

        if(active)
        {
            new BukkitRunnable() {
                @Override
                public void run() {

                    ItemStack itemStack = new ItemStack(material);

                    if (isFuel) {
                        generatedItem = location.getWorld().dropItem(location, itemStack);
                    }

                    boolean foundCoal = false;

                    for (int i = 0; i < inventory.getSize(); i++) {
                        ItemStack item = inventory.getItem(i);

                        if (item != null && item.getType() == Material.COAL) {
                            int cAmount = item.getAmount();


                            if (cAmount > 1) {
                                item.setAmount(cAmount - 1);
                            } else if (cAmount == 1) {
                                inventory.setItem(i, null);
                            }

                            foundCoal = true;
                            isFuel = true;
                            armorStand.setCustomName(ChatColor.GREEN + "Fuel");
                            break;
                        }
                    }

                    if (!foundCoal) {
                        armorStand.setCustomName(ChatColor.RED +"NoFuel");
                        isFuel = false;
                    }
                }
            }.runTaskTimer(plugin, 0, 100);

        }

        plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }



    public void startGenerator() {
        Map<String, GeneratorConfig> generatorsConfig = YMLReader.loadGenerators(plugin,"config.yml");

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
            armorStand.remove();
            active = false;
            isFuel = false;
            inventory.clear();
            inventory = null;

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
