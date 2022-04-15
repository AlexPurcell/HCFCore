package com.C200;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class HelloWorld implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Material type = event.getBlock().getType();
        if (type == Material.TORCH) {
            Player player = event.getPlayer();
            player.sendMessage("Hello World!");
        }
    }

    @EventHandler
    public void onCommandDisabled(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();
        if(message.startsWith("/version")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "HCF" + ChatColor.DARK_PURPLE + "]" + " Server is currently running on version" + ChatColor.WHITE + " v1.00");
        }
    }


}
