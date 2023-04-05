package org.bitcash.commiteconomy.menu.guis;

import org.bitcash.commiteconomy.menu.InventoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class GUIMain2 extends InventoryGUI {

    /*
     * Per standard, only one instance should ever be made, and it is highly recommended to do so within the class itself to keep track
     * Configuration parameters will be placed here
     */
    private static GUIMain2 instance = new GUIMain2(); 


    private GUIMain2() {
        // Will eventually be pulled from a configuration file
        int configuredGuiSize = 27;
        String configuredGuiTitle = "Test GUI";
        inv = InventoryGUI.buildInventory(this, configuredGuiSize, configuredGuiTitle);
    }

    private void testrun() {
        addItem(20,Material.DIAMOND_ORE,"&A&LLETS GOOOOO","&eHELL YEAHHHHHH","&eLETS GOOOOOOO","&cYEEEEEE");
    }

    public static void openGUI(Player player) {
        instance.testrun();
        player.openInventory(instance.inv);
    }



    public void onClick(InventoryClickEvent e) {}
    public void onClose(InventoryCloseEvent e) {}
    public void onOpen(InventoryOpenEvent e) {}

}