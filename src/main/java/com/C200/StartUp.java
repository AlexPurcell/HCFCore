package com.C200;

import com.C200.commands.Feed;
import com.C200.commands.Heal;
import com.C200.commands.gameMode;
import com.mongodb.client.*;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import io.github.cdimascio.dotenv.Dotenv;

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
            doUpdates();



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
        var uri = dotenv.get("MY_ENV_VAR1");

        MongoClient clientURI = MongoClients.create(uri);


        MongoDatabase db = clientURI.getDatabase("Core");


        MongoCollection<Document> col = db.getCollection("permissionsNode");

        MongoChangeStreamCursor<ChangeStreamDocument<Document>> cursor = col.watch().cursor();
        System.out.println("Watching");

        ChangeStreamDocument<Document> next = cursor.next();
        System.out.println("Okay now!");

        System.out.println(next);

        cursor.close();

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