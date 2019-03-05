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


        Results = getReport25();

        // Display results
        app.displayResults(Results);

        // Disconnect from database
        app.disconnect();
    }

    // 25. As a service user I want to produce a report listing the population of people,
    // people living in cities, and people not living in cities in each country


    public static String getReport1()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            // R1 - All the countries in the world organised by largest population to smallest.


            String strSelect = "SELECT Code, country.Name, Continent, Region, country.Population, (SELECT name FROM city WHERE ID = Capital) as Capital " +
                    "            FROM country\n" +
                    "            ORDER BY country.Population DESC ";

            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Code:" + "\t" +"Name:" + "\t" + "Continent:" + "\t" + "Region:" + "\t" + "Population:"  + "\t" +  "Capital:" );

            while (rset.next())
            {
                world wd = new world();

                // Fields to be shown
                wd.Code = rset.getString("Code");
                wd.Name = rset.getString("name");
                wd.Continent = rset.getString("Continent");
                wd.Region = rset.getString("Region");
                wd.Population = rset.getString("Population");
                wd.Capital = rset.getString("Capital");


                System.out.println( wd.Code + "\t" + wd.Name  + "\t" + wd.Continent  + "\t" + wd.Region  + "\t" + wd.Population  + "\t" + wd.Capital);
                String newRES =  wd.Code + "\t" + wd.Name  + "\t" + wd.Continent  + "\t" + wd.Region  + "\t" + wd.Population  + "\t" + wd.Capital;

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

    public static String getReport25()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "select country.Name as 'Country', SUM(city.Population) AS City , " +
                    " country.Population - SUM(city.Population) as Urban  , country.Population AS Total " +
                    " from city " +
                    " inner join country on country.Code = city.CountryCode " +
                    " where countryCode = city.CountryCode " +
                    " group by  country.Population, country.Name ";


            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Country" + "\t" + "Total:" + "\t" + "City:" + "\t" +  "Urban:" );

            while (rset.next())
            {
                world wd = new world();

                // Fields to be shown
                wd.Country = rset.getString("Country");
                wd.total = rset.getString("total");
                wd.city = rset.getString("city");
                wd.urban = rset.getString("urban");
               //// wd.Name = rset.getString("Name");
              //  wd.Population = rset.getString("Population");



                System.out.println( wd.Country + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban );
                String newRES =     wd.Country + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban +"\n";

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
    public void displayResults(String results)
    {
        if (results != null) {
            System.out.println(
                    results + "\n");

        }
    }
}



