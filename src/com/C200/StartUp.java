package com.C200;

import com.C200.commands.Feed;
import com.C200.commands.Heal;
import com.C200.commands.gameMode;
import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.StaffModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class StartUp extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("Hello world!");

        Bukkit.getPluginManager().registerEvents(new EventHandle(), this);
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());
        getCommand("gmc").setExecutor(new gameMode());
        getCommand("gms").setExecutor(new gameMode());
        getCommand("gma").setExecutor(new gameMode());
        getCommand("gmspec").setExecutor(new gameMode());


    }

    @Override
    public void onDisable() {
        System.out.println("Shutting Down!");
        }
}

// 12:20 ep 3!!!!!!!!!!!!!!!!!