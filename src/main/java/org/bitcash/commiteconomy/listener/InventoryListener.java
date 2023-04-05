package org.bitcash.commiteconomy.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import static org.bitcash.commiteconomy.menu.InventoryGUI.REGISTERED_GUIS;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {

        Inventory env = e.getInventory();
        if (!(REGISTERED_GUIS.containsKey(env))) return;
        
        e.setCancelled(true);

        REGISTERED_GUIS.get(env).onClick(e);

        
    }

    @EventHandler
    public void onCloseEvent(InventoryCloseEvent e) {
        Inventory env = e.getInventory();
        if (!(REGISTERED_GUIS.containsKey(env))) return;
        
        REGISTERED_GUIS.get(env).onClose(e);
    }

    @EventHandler
    public void onOpenEvent(InventoryOpenEvent e) {
        Inventory env = e.getInventory();
        if (!(REGISTERED_GUIS.containsKey(e))) return;

        REGISTERED_GUIS.get(env).onOpen(e);
    }

    /*
     * EventHandlers that are only cancelling events: 
     */
    @EventHandler
    public void cancelDragEvent(InventoryDragEvent e) {
        Inventory env = e.getInventory();
        if (!(REGISTERED_GUIS.containsKey(env))) return;
        
        e.setCancelled(true);
    }





    
}
