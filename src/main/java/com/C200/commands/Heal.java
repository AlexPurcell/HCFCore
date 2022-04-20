package com.C200.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;
        player.setHealth(20.0d);
        player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Health has been regenerated!");
        return true;
    }
}
