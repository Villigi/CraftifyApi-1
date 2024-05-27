package de.villigi.craftifyapi.database.addonAvailability;

import de.villigi.craftifyapi.CraftifyApi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonAvailabilityManager {

    private String addon;
    private AddonAvailabilitys availability;

    public AddonAvailabilityManager(String addon) {
        this.addon = addon;
    }

    public void setAvailability(AddonAvailabilitys availability) {
        //Set in Database
        this.availability = availability;
        try {
            PreparedStatement statement = CraftifyApi.getInstance().getDatabaseManager().getConnection().prepareStatement("UPDATE addon_availability SET Availability = '" + availability + "' WHERE Addon = '" + addon + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AddonAvailabilitys getAvailability() {
        String availabilityString = "";
        try {
            PreparedStatement statement = CraftifyApi.getInstance().getDatabaseManager().getConnection().prepareStatement("SELECT * FROM `addon_availability` WHERE Addon = ` " + addon + "`;");
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                availabilityString = resultSet.getString("Availability");
            }else{
                availability = AddonAvailabilitys.UNCLEAR;
                System.out.println("The Addon" + addon + " is not founded ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        availability = AddonAvailabilitys.valueOf(availabilityString);
        return availability;
    }

    public void addAddon(String addon, AddonAvailabilitys availability) throws SQLException {
        PreparedStatement statement = CraftifyApi.getInstance().getDatabaseManager().getConnection().prepareStatement("INSERT INTO addon_availability (Addon, Availability) VALUES ('" + addon + "', '" + availability.toString() + "');");
        statement.executeUpdate();
    }
}
