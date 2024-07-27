package de.villigi.plugbyte.api;

import de.villigi.plugbyte.PlugByteApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlApi {
    public static void updatePreparedStatement(String statement) {
        try {
            PreparedStatement statemnt1 = PlugByteApi.getInstance().getDatabaseManager().getConnection().prepareStatement(statement);
            statemnt1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnectionOfApi() {
        return PlugByteApi.getInstance().getDatabaseManager().getConnection();
    }
}
