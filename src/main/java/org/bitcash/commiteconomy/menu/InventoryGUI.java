package org.bitcash.commiteconomy.menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public abstract class InventoryGUI {

    /*
     * EventHandlers will only set cancellation state and run an InventoryGUIs if the provided Inventory is run.
     */
    public static final Map<Inventory,InventoryGUI> REGISTERED_GUIS = new HashMap<>();

    /*
     * 
     */
    // public abstract void initialize();

    /*
     * runs InventoryClickEvent
     */
    public abstract void onClick(InventoryClickEvent e);
    
    /*
     * runs InventoryClickEvent
     */
    public abstract void onClose(InventoryCloseEvent e);
    
    /*
     * runs InventoryClickEvent
     */
    public abstract void onOpen(InventoryOpenEvent e);

    /*
     * an extending class will initialize via
     * InventoryBuilder.buildInventory(InventoryGUI invgui, int size, String name)
     * 
     * IllegalStateException will be thrown if an InventoryGUIs instance does not initialize this field first.
     */

     /*
      * Only its concrete subclasses can be instantiated.
      */
    protected Inventory inv = null;

    /*
     * Builds the inventory and puts it in REGISTERED_GUIS for listeners to access 
     * a subclass should never instantiate without the invocation of this method.
     */
    public static Inventory buildInventory(InventoryGUI invgui, int size, String name) {
        Inventory inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', name));
        InventoryGUI.REGISTERED_GUIS.put(inventory, invgui);

        return inventory;
    }
    /*
     * Constructs an ItemStack, sets its corresponding metadata and stores it in the provided inventory.
     * 
     * It is the responsibility of the subclass to allow YAML configurability for these provided values.
     * 
     * Throws NPE same reason as above.
     */
    public void addItem(int slot, Material material, String name, String... lore) {
        if (inv == null || !(inv instanceof Inventory)) {
            throw new NullPointerException("The provided InventoryGUI was not instantiated correctly. All instances must call InventoryBuilder.buildInventory(InventoryGUI invgui, int size, String name)");
        }
        final ItemStack item = new ItemStack(material);

        for (int i = 0; i < lore.length; i++) {
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }

        ItemMeta metadata = item.getItemMeta();
        metadata.setDisplayName(ChatColor.translateAlternateColorCodes('&', name)); // easy to make configurable later
        metadata.setLore(Arrays.asList(lore)); // also easy to configure later

        item.setItemMeta(metadata);

        //store it to the subclass' provided inventory
        inv.setItem(slot, item);

    }
    /*
     * Overloaded to accept parameter specifying quantity of stored ItemStack
     */
    public void addItem(int slot, Material material, int qty, String name, String... lore) {
        if (inv == null || !(inv instanceof Inventory)) {
            throw new NullPointerException("The provided InventoryGUI was not instantiated correctly. All instances must call InventoryBuilder.buildInventory(InventoryGUI invgui, int size, String name)");
        }
        final ItemStack item = new ItemStack(material, qty);

        for (int i = 0; i < lore.length; i++) {
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }

        ItemMeta metadata = item.getItemMeta();
        metadata.setDisplayName(ChatColor.translateAlternateColorCodes('&', name)); 
        metadata.setLore(Arrays.asList(lore));

        item.setItemMeta(metadata);

        // store it to the subclass' provided inventory
        inv.setItem(slot, item);

    }

    public void setFiller(Material fillerMaterial, String displayName) {
        if (inv == null || !(inv instanceof Inventory)) {
            throw new NullPointerException("The provided InventoryGUI was not instantiated correctly. All instances must call InventoryBuilder.buildInventory(InventoryGUI invgui, int size, String name)");
        }
        ItemStack filler = new ItemStack(fillerMaterial);
        ItemMeta metadata = filler.getItemMeta();
        metadata.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        filler.setItemMeta(metadata);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, filler);
            }
        }
    }
    
}
