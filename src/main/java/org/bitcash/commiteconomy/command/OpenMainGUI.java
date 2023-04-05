package org.bitcash.commiteconomy.command;

import org.bitcash.commiteconomy.menu.guis.GUIMain2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.ChatColor;

public class OpenMainGUI implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "This command can only be accessed by players ingame.");
            return true;
        }

        Player player = (Player) sender;

        GUIMain2.openGUI(player);

        return true;
    }


    
}
