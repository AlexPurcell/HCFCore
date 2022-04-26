package com.C200;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.StaffModule;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EventHandle implements Listener {


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

    @EventHandler
    public void customJoinMessage(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "+" + ChatColor.DARK_PURPLE + "] " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.DARK_PURPLE + " has joined the server!");
        Player player = event.getPlayer();
        LunarClientAPI api = LunarClientAPI.getInstance();

        api.setStaffModuleState(player, StaffModule.XRAY, true);

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Dotenv dotenv = Dotenv.configure()
                .directory("C:\\Users\\alext\\IdeaProjects\\Core\\src\\main\\assets")
                .filename(".env")
                .load();

        String uri = dotenv.get("MY_ENV_VAR1");

        assert uri != null;
        MongoClient clientURI = MongoClients.create(uri);
        Player p = event.getPlayer();
        if(!(p.hasPlayedBefore())) {
            MongoDatabase db = clientURI.getDatabase("Core");
            MongoCollection<Document> col = db.getCollection("permissionsNode");
            String playername = event.getPlayer().getName();
            String uuid = event.getPlayer().getUniqueId().toString();

            Document CS1 = new Document("Name", playername)
                    .append("UUID", uuid);

            List<String> permissionList = new ArrayList<>();
            for (PermissionAttachmentInfo attachment : p.getEffectivePermissions()) {
                permissionList.add(attachment.getPermission());
            }
            CS1.append("Permissions", permissionList);

            // CS = Constructor

            col.insertOne(CS1);

        }

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> futureTask = threadPool.submit(StartUp::doUpdates);

        if (futureTask.isDone()) {
            System.out.println("Database Updated!");
        } else {
            System.out.println("Searching for Updates..");
        }
    }

    @EventHandler
    public void enderPearls(ProjectileHitEvent event) {
        World world = Bukkit.getServer().getWorld("world");
        if(event.getEntityType().equals(EntityType.ENDER_PEARL) & event.getEntity().isDead()) {
            Location location = event.getEntity().getLocation();
            System.out.println(location);
        }
    }
}
