package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    /**
     * Connection to MySQL database.
     */

    private static Connection con = null;

    public ArrayList<Employee> allSalaries;


    // DON'T CHANGE
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
     * * Disconnect from the MySQL database. DON'T CHANGE
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
        System.out.println("version 1");
        app.connect();

        String Results = "";


        Results = getReport16();

        // Display results
//        app.displayResults(Results);

        // Disconnect from database
        app.disconnect();
    }

    // REPORT 16:  produce a report listing the top N populated cities in a district where N is provided by the user
    public static String getReport16()
    {
        // Create user input variable
        int userInput = 5;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.Name, city.Population " +
                                " FROM city " +
                                " JOIN country ON city.CountryCode=country.Code " +
                                " WHERE district = 'Tabasco' " +
                                " ORDER BY city.Population DESC " +
                                " LIMIT " + userInput;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** The top " +  userInput + " most populated cities in Tabasco, Mexico: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                world wd = new world();

                // Fields to be shown
               // wd.Country = rset.getString("Country");
                wd.Name = rset.getString("Name");
                wd.Population = rset.getString("Population");



                System.out.println( wd.Name + "\t" + wd.Population );
                String newRES =   wd.Name + "\t" + wd.Population +"\n";

                // Build Results
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


    // DON'T CHANGE
//    public void displayResults(String results)
//    {
//        if (results != null) {
//            System.out.println(
//                    results + "\n");
//
//        }
//    }
}



