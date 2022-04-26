package com.C200;

import ch.qos.logback.classic.LoggerContext;
import com.C200.commands.Feed;
import com.C200.commands.Heal;
import com.C200.commands.gameMode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClients;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ch.qos.logback.classic.Level;

import io.github.cdimascio.dotenv.Dotenv;

import static com.mongodb.client.model.Projections.*;
import static java.lang.Thread.sleep;

public class StartUp extends JavaPlugin {

    public static void display()  {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<String> futureTask = threadPool.submit(StartUp::doUpdates);
        System.out.println("Thread started.");
        if (futureTask.isDone()) {
            System.out.println("Database Updated!");
        } else {
            System.out.println("Searching for Updates..");

        }

    }


    @Override
    public void onEnable() {
        System.out.println("Hello world!");
        Bukkit.getPluginManager().registerEvents(new EventHandle(), this);
        getCommand("feed").setExecutor(new Feed());
        getCommand("heal").setExecutor(new Heal());
        getCommand("gmc").setExecutor(new gameMode());
        getCommand("gms").setExecutor(new gameMode());
        getCommand("gma").setExecutor(new gameMode());
        getCommand("gmspec").setExecutor(new gameMode());
        LoggerContext context = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        (context).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
    }

    @Override
    public void onDisable() {
        System.out.println("Shutting Down!");
    }

    public static String doUpdates() {
        Dotenv dotenv = Dotenv.configure()
                .directory("C:\\Users\\alext\\IdeaProjects\\Core\\src\\main\\assets")
                .filename(".env")
                .load();

        System.out.println("doUpdates activated");
        String uri = dotenv.get("MY_ENV_VAR1");

        assert uri != null;

        MongoClient clientURI = MongoClients.create(uri);
        MongoDatabase db = clientURI.getDatabase("Core");
        MongoCollection<Document> col = db.getCollection("permissionsNode");
//        List<Bson> pipeline = singletonList(match(eq("operationType", "update")));

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        MongoChangeStreamCursor<ChangeStreamDocument<Document>> cursor = col.watch().cursor();

        System.out.println("Watching stream");
        ChangeStreamDocument<Document> next = cursor.next();
        assert next.getUpdateDescription() != null;
        System.out.println("Database has been updated! --> " + next.getUpdateDescription());

        BasicDBObject query = new BasicDBObject();

        try{
            Bson projection = fields(include("Name"), exclude("_id"));
            col.find(query).projection(projection).forEach(document -> {
                    JsonObject obj = new JsonParser().parse(document.toJson()).getAsJsonObject();
                    String name = obj.get("Name").getAsString();
                    Player p = Bukkit.getServer().getPlayerExact(name);
            List<String> listOfUpdates = new ArrayList<>();
            var updatedID = next.getDocumentKey();
            assert updatedID != null;
            var newDoc = col.find(updatedID);
            JsonObject myNewObj = new JsonParser().parse(newDoc.toString()).getAsJsonObject();
            String getUpdatedname = myNewObj.get("Name").getAsString();


             listOfUpdates.add(getUpdatedname);
             for (int i = 0; i < listOfUpdates.size(); i++) {
                  if (listOfUpdates.get(i).toString().equals(p.getName())) {
                        System.out.println("true");
                  } else {
                      System.out.println("false");
                      System.out.println(listOfUpdates);
                    }

                        //parse array to string and see if any object is equal to player name
           }



//                if(p != null) {
//                    p.sendMessage(ChatColor.RED + "(" + ChatColor.WHITE + "From Console" + ChatColor.RED + ")" + ChatColor.WHITE + " Permissions have been updated!");
//                } else {
//                    System.out.println("Player is either offline or has never joined!");
//                }

            });
            cursor.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        display();
        return "true";
    }


}
// 12:20 ep 3!!!!!!!!!!!!!!!!!