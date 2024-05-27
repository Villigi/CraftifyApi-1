package de.villigi.craftifyapi;

import de.villigi.craftifyapi.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class CraftifyApi extends JavaPlugin {

    private static CraftifyApi instance;
    public DatabaseManager databaseManager;


    @Override
    public void onEnable() {
        instance = this;
        databaseManager = new DatabaseManager();
        databaseManager.loadFiles();
        try {
            databaseManager.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static CraftifyApi getInstance() {
        return instance;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
