package de.villigi.plugbyte;

import de.villigi.plugbyte.api.MessageApi;
import de.villigi.plugbyte.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class PlugByteApi extends JavaPlugin {

    private static PlugByteApi instance;
    public DatabaseManager databaseManager;

    public static String prefix = " ";


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

        register();

        prefix = new MessageApi("prefix").getMessage();
    }

    public void register() {
        new MessageApi("prefix").addMessage("§bPlugByte §7| ");
        new MessageApi("message.noperms").addMessage("§cDazu hast du keine Rechte!");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static PlugByteApi getInstance() {
        return instance;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
