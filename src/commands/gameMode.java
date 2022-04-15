package commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gameMode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only player's can use that command!");
            return false;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("gmc")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Gamemode has been updated to" + ChatColor.WHITE + " CREATIVE");
            return true;
        }
        else if (command.getName().equalsIgnoreCase("gms")) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Gamemode has been updated to" + ChatColor.WHITE + " SURVIVAL");
            return true;
        }
        else if (command.getName().equalsIgnoreCase("gma")) {
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Gamemode has been updated to" + ChatColor.WHITE + " ADVENTURER");
            return true;
        }
        else if (command.getName().equalsIgnoreCase("gmspec")) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Gamemode has been updated to" + ChatColor.WHITE + " SPECTATOR");
            return true;
        }
        else {
            player.sendMessage(ChatColor.RED + "Invalid operation!");
            return true;
        }

    }
}
