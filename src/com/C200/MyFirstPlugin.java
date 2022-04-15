package com.C200;

import commands.Feed;
import commands.Heal;
import commands.gameMode;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MyFirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("Hello world!");

        Bukkit.getPluginManager().registerEvents(new HelloWorld(), this);
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());
        getCommand("gmc").setExecutor(new gameMode());
        getCommand("gms").setExecutor(new gameMode());
        getCommand("gma").setExecutor(new gameMode());
        getCommand("gmspec").setExecutor(new gameMode());
    }

    @Override
    public void onDisable() {
        System.out.println("Shutting Down!!!!!!!");

        }
}