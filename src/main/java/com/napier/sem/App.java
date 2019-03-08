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
        System.out.println("version r9");
        app.connect();

        // local variable
        String Results1 , Results3 , Results5 , Results7, Results9 = "";

        // Report 1
        //Results1 = getReport1();
        // Report 3
        //Results3 = getReport3();
        // Reports 5
        //Results5 = getReport5(10);
        // Report 7
        //Results7 = getReport7();
        // Report 9
        Results9 = getReport9();


        // Display results
        //app.displayResults(Results1);
        //app.displayResults(Results3);
        //app.displayResults(Results5);
        //app.displayResults(Results7);
        app.displayResults(Results9);




        // Disconnect from database
        app.disconnect();
    }

    // REPORT 1: All the countries in the world organised by largest population to smallest.
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
                wd.code = rset.getString("Code");
                wd.name = rset.getString("name");
                wd.continent = rset.getString("Continent");
                wd.region = rset.getString("Region");
                wd.population = rset.getString("Population");
                wd.capital = rset.getString("Capital");


                System.out.println( wd.code + "\t" + wd.name  + "\t" + wd.continent  + "\t" + wd.region  + "\t" + wd.population  + "\t" + wd.capital);
                String newRES =  wd.code + "\t" + wd.name  + "\t" + wd.continent  + "\t" + wd.region  + "\t" + wd.population  + "\t" + wd.capital;

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

    // REPORT 3:
    public static String getReport3()
    {
        String results = "";
        try
        {
            // SELECT STATEMENT - THIS GETS CHANGED

            String strSelect = "SELECT Region,Code, country.Name, Continent,  country.Population, (SELECT name FROM city WHERE ID = Capital) as Capital " +
                    " FROM country  group by Region, Code, country.Name, Continent, country.Population,Capital " +
                    " order by Region, Population DESC";


            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Code"  +"\t" + "Name:" + "\t" + "Continent:" + "\t" + "Region:" + "\t" + "Population:" + "\t" + "Capital:");

            while (rset.next())
            {
                world wd = new world();
                wd.code = rset.getString("Code");
                wd.name = rset.getString("Name");
                wd.continent = rset.getString("Continent");
                wd.region = rset.getString("Region");
                wd.population = rset.getString("Population");
                wd.capital = rset.getString("Capital");

                System.out.println( wd.code + "\t" +wd.name + "\t" +wd.continent + "\t" +wd.region + "\t" +wd.population + "\t" + wd.capital + "\n" );
                String newRES =wd.code + "\t" +wd.name + "\t" +wd.continent + "\t" +wd.region + "\t" +wd.population + "\t" + wd.capital + "\n";

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

    // REPORT 5 : The top N populated countries in a continent where N is provided by the user.
    public static String getReport5(Integer num)
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            // https://docs.microsoft.com/en-us/sql/t-sql/statements/create-partition-function-transact-sql?view=sql-server-2017

            String strSelect = "WITH RowSETS AS ( SELECT Code, country.Name, Continent, Region, country.Population, (SELECT name FROM city WHERE ID = Capital) as Capital, " +
                    " ROW_NUMBER() over ( PARTITION BY CONTINENT ORDER BY Population DESC ) AS RowNum " +
                    " from country ) " +
                    " select * from RowSETS where RowNum <= " + num ;

            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Code"  +"\t" + "Name:" + "\t" + "Continent:" + "\t" + "Region:" + "\t" + "Population:" + "\t" + "Capital:");

            while (rset.next())
            {
                world wd = new world();
                wd.code = rset.getString("Code");
                wd.name = rset.getString("Name");
                wd.continent = rset.getString("Continent");
                wd.region = rset.getString("Region");
                wd.population = rset.getString("Population");
                wd.capital = rset.getString("Capital");

                System.out.println( wd.code + "\t" +wd.name + "\t" +wd.continent + "\t" +wd.region + "\t" +wd.population + "\t" + wd.capital + "\n" );
                String newRES = wd.code + "\t" +wd.name + "\t" +wd.continent + "\t" +wd.region + "\t" +wd.population + "\t" + wd.capital + "\n";

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

    // REPORT 7 : All the cities in the world organised by largest population to smallest.

    public static String getReport7()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT

            String strSelect = "select city.Name , country.Name as Country, District, city.Population  " +

                    " from city " +
                    " inner join country on country.Code = city.CountryCode " +

                    " ORDER BY city.Population DESC " ;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned (header)
            System.out.println( "Name:" + "\t" + "Country:"+ "\t" + "District:"+ "\t" + "Population:");

            while (rset.next())
            {
                world wd = new world();

                // Fields to be shown
                wd.name = rset.getString("Name");
                wd.country = rset.getString("Country");
                wd.district = rset.getString("District");
                wd.population = rset.getString("Population");

                System.out.println( wd.name + "\t" + wd.country+ "\t" + wd.district+ "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.country+ "\t" + wd.district+ "\t" + wd.population +"\n";

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

    // REPORT 9: All the cities in a region organised by largest population to smallest.

    public static String getReport9()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT

            String strSelect = "select city.Name , country.Name as Country, District, city.Population  " +

                    " from city " +
                    " inner join country on country.Code = city.CountryCode " +

                    " ORDER BY Region, Population DESC" ;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Name" + "\t" + "Country:" + "\t" + "District:"+ "\t" + "Population:");

            while (rset.next())
            {
                world wd = new world();

                // Fields to be shown
                wd.name = rset.getString("Name");
                wd.country = rset.getString("Country");
                wd.district = rset.getString("District");

                wd.population = rset.getString("Population");


                System.out.println( wd.name + "\t" + wd.country + "\t" +wd.district + "\t" + wd.population );
                String newRES =  wd.name + "\t" + wd.country + "\t" +wd.district + "\t" + wd.population +"\n";

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



