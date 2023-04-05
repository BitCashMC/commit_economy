package org.bitcash.commiteconomy;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIMain implements Listener {

    private static GUIMain mainGuiInstance = new GUIMain();

    /* Get the instance of this GUI being used by the server
     * 
    */
    public static GUIMain getMainGuiInstance() {
        return mainGuiInstance; 
    }

    // protected GUIMain() {} //Listener registration
    // /*
    //  * Should only be null if registering event
    //  */
    private final Inventory gui = Bukkit.createInventory(null, 9, ChatColor.DARK_BLUE + "Shop by BitCash");
    // private Player player = null; 

    private GUIMain() {
        initializeIcons();
    }

    public void initializeIcons() {
        gui.addItem(addItem(Material.DIAMOND,"§bGeneric ERC20","§7A very nice token on the ethereum blockchain",
        "§7there are currently no security measures"));
        
    }

    /*
     * Convenient method to add items to the GUI.
     * thank you varargs for not having to make me overload this method for no
     * lore!
     */
    private ItemStack addItem(Material material, String name, String... lore) { 
        ItemStack item = new ItemStack(material);
        ItemMeta metadata = item.getItemMeta();
        
        metadata.setDisplayName(name);
        metadata.setLore(Arrays.asList(lore));

        item.setItemMeta(metadata);

        return item;
    }

    /*
     * The method to have a player access this GUI, and is what CommandOpenMainGUI calls.
     */
    public void open(Player player) {
        player.openInventory(gui);
    }

    @EventHandler
    public void cancelDragEvent(InventoryDragEvent e) {
        if (!(e.getInventory() == gui)) return;

        e.setCancelled(true);
        Bukkit.broadcastMessage("inventorydragevent called");
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {
        if (!(e.getInventory() == gui)) return;
        Bukkit.broadcastMessage("inventoryclickevent called");
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        player.sendMessage("debug - " + e.getClick());
        if (e.getClick() == ClickType.RIGHT) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[BitCore]&a Right clicking will sell the item!"));
            // player.playSound(player.getLocation(), Sound., null, 0, 0);            
        } else if (e.getClick() == ClickType.LEFT) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l[BitCore]&a Left clicking will buy the item!"));
        }
    }
    
}
