package org.bitcash.commiteconomy;

import org.bitcash.commiteconomy.command.OpenMainGUI;
import org.bitcash.commiteconomy.listener.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CommitEconomy extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getLogger().info("CommitEconomy v1.0 has been enabled successfully.");
        getCommand("shop").setExecutor(new OpenMainGUI());
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("CommitEconomy v1.0 has been disabled successfully.");
    }

}
