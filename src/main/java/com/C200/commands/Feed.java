package com.C200.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;
        if(args.length > 1) {
            player.sendMessage(ChatColor.RED + "Incorrect format, please use: /feed <Player>");
            return true;
        }
        if(args.length == 1) {
            Player typedUser = Bukkit.getPlayer(args[0]);
            if (typedUser == null) {
                player.sendMessage(ChatColor.RED + "Invalid player!");
                return true;
            }
            if (!(typedUser == player)) {
                typedUser.setFoodLevel(20);
                typedUser.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Hunger has been regenerated!");
                player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "] " + ChatColor.DARK_PURPLE + typedUser.getName() + " has received hunger!");
            } else {
                player.setFoodLevel(20);
                player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Hunger has been regenerated!");
            }
            return true;
        } else{
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Hunger has been regenerated!");
        }

        return true;


    }
}
