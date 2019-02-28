package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    /**
     * Connection to MySQL database.
     */


    private static Connection con = null;

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:MySQL://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("SQLState: " + sqle.getSQLState());
                System.out.println("VendorError: " + sqle.getErrorCode());


                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

    }

    /**
     * * Disconnect from the MySQL database.
     */

    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


    public static  void main(String[] args) {
        // Create new Application
        App app = new App();
        System.out.println("version 2");
        app.connect();

        // Create variable to hold the data returned
        String Results = "";

        // Get World Data for report 3
        Results = getReport3();

        // Display results
        app.displayResults(Results);

        // Disconnect from database
        app.disconnect();
    }

    // REPORT 3 : All the countries in a region organised by largest population to smallest.
    public static String getReport3()
    {
        String results = "";
        try
        {
            // SELECT STATEMENT - THIS GETS CHANGED

            String strSelect = "SELECT Region, name As Name, Population " +
            " FROM country  group by Region, name, Population " +
            " order by Region, Population DESC";

            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Region"  +"\t" + "Name:" + "\t" + "Population:");

            while (rset.next())
            {
                world wd = new world();

               // wd.Continent = rset.getString("continent");
                wd.Name = rset.getString("name");
                wd.Population = rset.getString("population");
                wd.Region = rset.getString("region");
                System.out.println( wd.Region + "\t" +wd.Name + "\t" + wd.Population + "\n" );
                String newRES = wd.Region + "\t" +wd.Name + "\t" + wd.Population + "\n";

                results = results + newRES;

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world details");
            return null;
        }
        return results;
    }


    // Method used to display the results from the queries
    public void displayResults(String results) {

        if (results != null) {
            System.out.println(
                    results + "\n");
        }
    }

}



