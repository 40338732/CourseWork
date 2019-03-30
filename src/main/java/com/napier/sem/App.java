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
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    // updated to bring evens and odds together



    public static  void main(String[] args) {
        // Create new Application
        App app = new App();
        System.out.println("version r25");
        app.connect();

        // local variable
        String Results1 , Results3 , Results5 , Results7, Results9, Results11, Results13 , Results15, Results17 , Results19, Results21 , Results23 , Results25 = "";
        String Results2 , Results4 , Results6 , Results8, Results10, Results12, Results14 , Results16, Results18 , Results20, Results22 , Results24  = "";
        String PopReport1, PopReport2, PopReport3, PopReport4, PopReport5, PopReport6, PopReport7, PopReportStats;

        //Results1 = getReport1();
        //Results2 = getReport3();
        //Results3 = getReport3();
       // Results4 = getReport4(6);
       // Results5 = getReport5(10);
        //Results6 = getReport6();
        //Results7 = getReport7();
        //Results8 = getReport8();
        //Results9 = getReport9();
        //Results10 = getReport10();
        //Results11 = getReport11();
        //Results12 = getReport12();
        //Results13 = getReport13(10);
        //Results14 = getReport14();
        //Results15 = getReport15(10);
        //Results16 = getReport16();
        //Results17 = getReport17();
        //Results18 = getReport18();
        //Results19 = getReport19();
        //Results20 = getReport20();
        //Results21 = getReport21(10);
        //Results22 = getReport22();
        //Results23 = getReport23();
        //Results24 = getReport24();
        //Results25 = getReport25();

        // World Reports
        PopReport1 = getPopReport1();
        PopReport2 = getPopReport2();

        // Display results

        //app.displayResults(Results1);
        //app.displayResults(Results2);
        //app.displayResults(Results3);
       // app.displayResults(Results4);
        //app.displayResults(Results5);
        //app.displayResults(Results6);
        //app.displayResults(Results7);
        //app.displayResults(Results8);
        //app.displayResults(Results9);
        //app.displayResults(Results10);
        //app.displayResults(Results11);
        //app.displayResults(Results12);
        //app.displayResults(Results13);
        //app.displayResults(Results14);
        //app.displayResults(Results15);
        //app.displayResults(Results16);
        //app.displayResults(Results17);
        //app.displayResults(Results18);
        //app.displayResults(Results19);
        //app.displayResults(Results20);
        //app.displayResults(Results21);
        //app.displayResults(Results22);
        //app.displayResults(Results23);
        //app.displayResults(Results24);
        //app.displayResults(Results25);

        // World Reports
        app.displayResultsWR(PopReport1);
        app.displayResultsWR(PopReport2);
        // Disconnect from database
        app.disconnect();
    }


    void printCountries(ArrayList<Country> countries)
    {
        // Check countries is not null
        if (countries != null)
        {
            System.out.println(String.format("%-10s %-15s %-20s", "Name", "Continent", "Population"));
            for (Country country : countries)
            {
                if (country == null)
                {
                    continue;
                }

                String country_string =
                        String.format("%-10s %-15s %-20s",
                                country.name, country.continent, country.population);
                System.out.println(country_string);
            }
        }
        else {
                System.out.println("No countries");
        }
    }



    // REPORT 1: All the countries in the World organised by largest population to smallest.
    public static String getReport1()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            // R1 - All the countries in the World organised by largest population to smallest.


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
                World wd = new World();

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
            System.out.println("Failed to get World details");
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
                World wd = new World();
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
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 5 : The top N populated countries in a continent where N is provided by the user.
    public static String getReport5(Integer num)
    {
        String results = "";
        System.out.println("Report5");
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
                World wd = new World();
                wd.code = rset.getString("Code");
                wd.name = rset.getString("Name");
                wd.continent = rset.getString("Continent");
                wd.region = rset.getString("Region");
                wd.population = rset.getString("Population");
                wd.capital = rset.getString("Capital");

                //System.out.println( wd.code + "\t" +wd.name + "\t" +wd.continent + "\t" +wd.region + "\t" +wd.population + "\t" + wd.capital + "\n" );
                String newRES = wd.code + "\t" +wd.name + "\t" +wd.continent + "\t" +wd.region + "\t" +wd.population + "\t" + wd.capital + "\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 7 : All the cities in the World organised by largest population to smallest.

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
                World wd = new World();

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
            System.out.println("Failed to get World details");
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
                World wd = new World();

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
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 11: All the cities in a district organised by largest population to smallest

    public static String getReport11()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            // All the cities in a district organised by largest population to smallest

            String strSelect = "select city.Name , country.Name as Country, District, city.Population  " +

                    " from city " +
                    " inner join country on country.Code = city.CountryCode " +

                    "ORDER BY District, Population DESC" ;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Name:" + "\t" + "Country:"+ "\t" + "District:"+ "\t" + "Population:");

            while (rset.next())
            {
                World wd = new World();

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
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 13: The top N populated cities in a continent where N is provided by the user.

    public static String getReport13(Integer num)
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "WITH RowSETS AS ( " +
                    "  SELECT city.Name,country.Name as Country, District, " +
                    " city.Population, " +
                    "   ROW_NUMBER() over (PARTITION BY CONTINENT ORDER BY city.Population DESC) AS RowNum " +
                    " from country, city where city.CountryCode = country.Code) " +

                    " select * from RowSETS where RowNum <=" + num;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Name:" + "\t" + "Country:"+ "\t" + "District:"+ "\t" + "Population:");

            while (rset.next())
            {
                World wd = new World();

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
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 15: The top N populated cities in a country where N is provided by the user.

    public static String getReport15(Integer num)
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "WITH RowSETS AS (" +
                    " SELECT city.Name, country.Name as 'Country', District, city.Population,  ROW_NUMBER() over " +
                    " ( PARTITION BY country.Name ORDER BY city.Population DESC ) AS RowNum " +
                    " from country, city where city.CountryCode = country.Code ) " +
                    " select * from RowSETS where RowNum <=" + num;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Name:" + "\t" + "Country:"+ "\t" + "District:"+ "\t" + "Population:");

            while (rset.next())
            {
                World wd = new World();

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
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 17: fixed All the capital cities in the World organised by largest population to smallest.

    public static String getReport17()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.name, country.Name as Country, city.Population " +
                    " FROM country join city on city.ID = country.Capital " +
                    " ORDER BY city.Population DESC ";


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "" + "\t" + "City:" + "\t" + "Country:"+ "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                wd.country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.country+ "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.country+ "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 19: : All the capital cities in a region organised by largest to smallest.

    public static String getReport19()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = " SELECT Region,city.Name,  city.Population  " +
                    " FROM country join city on city.ID = country.Capital " +
                    " ORDER BY Region, city.Population DESC ";


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Region" + "\t" + "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                wd.region = rset.getString("region");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.region+ "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.region+ "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 21: As a service user I want to produce a report listing the top N populated

    // capital cities in a continent where N is provided by the user

    public static String getReport21( Integer num)
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = " WITH RowSETS AS ( " +
                    " SELECT city.Name , country.Name as Country, District, " +
                    " city.Population, " +
                    " ROW_NUMBER() over (PARTITION BY country.Continent ORDER BY city.Population DESC) AS RowNum " +
                    " from country, city where city.CountryCode = country.Code) " +
                    " select * from RowSETS where RowNum <= " + num ;


            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "City" + "\t" + "Country:" + "\t" + "District:"+ "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                wd.country = rset.getString("Country");
                wd.district = rset.getString("District");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.country + "\t" + wd.district + "\t" + wd.population  );
                String newRES =     wd.name + "\t" + wd.country + "\t" + wd.district + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // REPORT 23: As a service user I want to produce a report listing the population of people,
    // people living in cities, and people not living in cities in each continent

    public static String getReport23()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "select Continent as Continent,  "+
                    " SUM(cast(country.Population AS UNSIGNED INTEGER )) As total ,  "+
                    " SUM(city.Population) as city ,  "+
                    " SUM( country.Population - city.Population ) as urban"+
                    " from city join country on  country.Code = city.CountryCode "+
                    " group by continent "+
                    " order by continent ASC ";


            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "Continent" + "\t" + "Total:" + "\t" + "City:" + "Urban" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                wd.continent = rset.getString("Continent");
                wd.total = rset.getString("total");
                wd.city = rset.getString("city");
                wd.urban = rset.getString("urban");
                //// wd.name = rset.getString("Name");
                //  wd.population = rset.getString("Population");



                System.out.println( wd.continent + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban );
                String newRES =     wd.continent + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    // 25. As a service user I want to produce a report listing the population of people,
    // people living in cities, and people not living in cities in each country

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
                World wd = new World();

                // Fields to be shown
                wd.country = rset.getString("Country");
                wd.total = rset.getString("total");
                wd.city = rset.getString("city");
                wd.urban = rset.getString("urban");
                //// wd.name = rset.getString("Name");
                //  wd.population = rset.getString("Population");



                System.out.println( wd.country + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban );
                String newRES =     wd.country + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }

    
    // ADDED EVEN NUMBERS FROM ROB
    // All Odd numbered reports copied into single file as git branch merges were overwriting the getReport method
    // Original branches were deleted

    // REPORT 2: list all the countries in a continent organised by largest population to smallest.
    public static String getReport2()
    {
        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT Continent, country.Name, Population " +
                    " FROM country " +
                    " WHERE Continent='Europe' " +
                    " ORDER BY Population DESC ";


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "Country:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }


    // REPORT 4: produce a report listing the top N populated countries in the World where N is provided by the user
    public static String getReport4(int userInput)
    {
        // Create user input variable
        //int userInput = 7;
        if (userInput <= 0)
        {
            System.out.println("Number must be positive");
            return "";
        }

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT Name, Population " +
                    " FROM country " +
                    " ORDER BY Population DESC " +
                    " LIMIT " + userInput;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "Country:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }



    // REPORT 6: produce a report listing the top N populated countries in a region where N is provided by the user
    public static String getReport6()
    {
        // Create user input variable
        int userInput = 7;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT Name, Region, Population " +
                    " FROM country " +
                    " WHERE region = 'Middle East' " +
                    " ORDER BY Population DESC " +
                    " LIMIT " + userInput;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** Top " + userInput + " populated countries in the Middle East: ***" );
            System.out.println( "Country:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }


    // REPORT 8: produce a report listing all the cities in a continent organised by largest population to smallest
    public static String getReport8()
    {
        // Create user input variable
        // int userInput = 7;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT Continent, city.Name, city.Population " +
                    " FROM city " +
                    " JOIN country ON city.CountryCode=country.Code " +
                    " WHERE Continent = 'Africa' " +
                    " ORDER BY city.Population DESC ";


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** Cities in Africa ordered by population: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }


    // REPORT 10: produce a report listing all the cities in a country organised by largest population to smallest
    public static String getReport10()
    {
        // Create user input variable
        // int userInput = 7;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.Name, city.Population " +
                    " FROM city " +
                    " JOIN country ON city.CountryCode=country.Code " +
                    " WHERE country.Name = 'United Kingdom' " +
                    " ORDER BY city.Population DESC ";


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** Cities in the UK ordered by population: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }


    // REPORT 12: produce a report listing the top N populated cities in the World where N is provided by the user
    public static String getReport12()
    {
        // Create user input variable
        int userInput = 20;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.Name, city.Population " +
                    " FROM city " +
                    " JOIN country ON city.CountryCode=country.Code " +
                    " ORDER BY city.Population DESC " +
                    "LIMIT " + userInput;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** The top " +  userInput + " most populated cities in the World: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }


    // REPORT 14:  produce a report listing the top N populated cities in a region where N is provided by the user
    public static String getReport14()
    {
        // Create user input variable
        int userInput = 10;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.Name, city.Population " +
                    " FROM city " +
                    " JOIN country ON city.CountryCode=country.Code " +
                    " WHERE region = 'South America' " +
                    " ORDER BY city.Population DESC " +
                    " LIMIT " + userInput;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** The top " +  userInput + " most populated cities in South America: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
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
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }



    // REPORT 18: produce a report listing all the capital cities in a continent organised by largest population to smallest
    public static String getReport18()
    {
        // Create user input variable
        // int userInput = 5;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.Name, city.Population " +
                    " FROM city " +
                    " JOIN country ON city.CountryCode=country.Code " +
                    " WHERE Continent = 'Europe' " +
                    " AND country.Capital=city.ID " +
                    " ORDER BY city.Population DESC ";


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** The most populated capital cities in Europe: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }



    // REPORT 20: produce a report listing the top N populated capital cities in the World where N is provided by the user
    public static String getReport20()
    {
        // Create user input variable
        int userInput = 12;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.Name, city.Population " +
                    " FROM city " +
                    " JOIN country ON city.CountryCode=country.Code " +
                    " WHERE country.Capital=city.ID " +
                    " ORDER BY city.Population DESC " +
                    " LIMIT " + userInput;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** The top " + userInput + " most populated capital cities in the World: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }



    // REPORT 22: produce a report listing the top N populated capital cities in a region where N is provided by the user
    public static String getReport22()
    {
        // Create user input variable
        int userInput = 8;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT city.Name, city.Population " +
                    " FROM city " +
                    " JOIN country ON city.CountryCode=country.Code " +
                    " WHERE country.Capital=city.ID " +
                    " AND Region = 'Caribbean' " +
                    " ORDER BY city.Population DESC " +
                    " LIMIT " + userInput;


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** The top " + userInput + " most populated capital cities in the Caribbean: ***" );
            System.out.println( "City:" + "\t" + "Population:" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                // wd.Country = rset.getString("Country");
                wd.name = rset.getString("Name");
                wd.population = rset.getString("Population");



                System.out.println( wd.name + "\t" + wd.population );
                String newRES =   wd.name + "\t" + wd.population +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
            return null;
        }
        return results;
    }


    // REPORT 24: produce a report listing the population of people, people living in cities, and people not living in cities in each region
    public static String getReport24()
    {
        // Create user input variable
        // int userInput = 8;

        String results = "";
        try
        {

            // SELECT STATEMENT
            String strSelect = "SELECT Region, sum(cast(country.Population AS UNSIGNED INTEGER )) AS Total, " +
                    " sum(city.Population) AS City, sum(country.Population - city.Population) AS Urban  " +
                    " FROM city JOIN country ON country.Code=city.CountryCode " +
                    " GROUP BY Region ";

//            String strTest = "SELECT sum(Population) FROM country GROUP BY Region";


            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            System.out.println( "\n" + "*** Region population, city population and urban population: ***" );
            System.out.println( "--Region--" + "\t--Total--" + "\t--City--" + "\t" + "--Urban--" );

            while (rset.next())
            {
                World wd = new World();

                // Fields to be shown
                //wd.Country = rset.getString("Country");
                //wd.name = rset.getString("Name");
                //wd.population = rset.getString("Population");
                wd.region = rset.getString("Region");
                wd.total = rset.getString("Total");
                wd.city = rset.getString("City");
                wd.urban = rset.getString("Urban");



                System.out.println( wd.region + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban );
                String newRES =    wd.region + "\t" + wd.total + "\t" + wd.city + "\t" + wd.urban +"\n";

                // Build Results
                results = results + newRES;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World details");
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
    // DON'T CHANGE
    private static void displayResultsWR(String results) {
        if (results != null) {
            System.out.println(
                    results + "\n");

        }
    }
    /// WORLD POPULATION REPORT

    // SELECT STATEMENTS FOR POPULATION REPORTS
    private static String reportTotalPopulation() {
        return "SELECT SUM(CAST( Population AS UNSIGNED INTEGER )) AS TotalPopulation FROM country";
    }

    private static String reportTotalPopulationContinents() {
        return "select SUM(CAST( Population AS UNSIGNED INTEGER)) AS TotalPopulation, Continent from country group by Continent";
    }

    private static String reportTotalPopulationRegion() {
        return "select SUM(CAST( Population AS UNSIGNED INTEGER)) AS TotalPopulation, Region from country group by Region";
    }

    private static String reportTotalPopulationCountry() {
        return "select SUM(CAST( Population AS UNSIGNED INTEGER)) AS TotalPopulation, Name As Country from country group by Name";
    }

    private static String reportTotalPopulationDistrictsCity() {
        return "select SUM(CAST( Population AS UNSIGNED INTEGER)) AS TotalPopulation, District from city group by District ";
    }

    private static String reportTotalPopulationCountryCity() {
        return "select SUM(CAST( Population AS UNSIGNED INTEGER)) AS TotalPopulation, Name from city group by Name ";
    }

    private static String reportTotalPopulationLanguage() {
        return "SELECT Name ,Language ,SUM(Percentage) as Percentage " +
                "  FROM countrylanguage " +
                "  inner join country on CountryCode = country.code " +
                "  where CountryCode = 'USA' " +
                "  group by name,Language " +
                "  order by Percentage DESC";
    }

    /* SQL Statement */
    private static String getStringPopulation() {
        return "SELECT Continent, Region, country.Name as Name, " +
                " sum(cast(country.Population AS UNSIGNED INTEGER )) AS totalPopulationCountry, " +
                " (sum(cast(country.Population AS UNSIGNED INTEGER )) - sum(cast(city.Population AS UNSIGNED INTEGER)) ) / (sum(cast(country.Population AS UNSIGNED INTEGER)) / 100 ) As percentageCountry, " +
                " sum(cast(country.Population as UNSIGNED INTEGER)) - SUM(CAST(city.Population as UNSIGNED INTEGER)) as notLivingInCities, " +
                " sum(cast(city.Population AS UNSIGNED INTEGER)) As totalPopulationCity, " +
                " 100 - (sum(cast(country.Population AS UNSIGNED INTEGER)) - sum(cast(city.Population AS UNSIGNED INTEGER)) ) / (sum(cast(country.Population AS UNSIGNED INTEGER )) / 100) As percentageCity " +
                " FROM city JOIN country ON country.Code=city.CountryCode " +
                " GROUP BY Continent,Region, country.Name  ";
    }

    /* Exceptions */
    private static String getException(Exception e) {
        String Exception;
        Exception = (e.getMessage());
        return Exception;
    }

    // ********* REPORTS - WORLD POPULATION *********
    /* Population 1 Report */
    private static String getPopReport1() {
        String results = "";
        try {
            // SELECT STATEMENT to pull information required for the reports
            String strSelect = reportTotalPopulation();

            // SQL Connect statements
            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            // This is the results set that is returned from the queries
            ResultSet set = stmt.executeQuery(strSelect);

            while (set.next()) {
                // New Instant of "World"
                World wd = new World();
                wd.totalPopulation = set.getString("TotalPopulation");

                String newRES = wd.totalPopulation + " \n";

                // Build Results
                results = results.concat( newRES );
            }

            // Check we have Data
            if (!(results == null)) {

                // Display the results from the queries
                displayResultsWR("R1: Total Population World" + "\n" + results);
                return null;
            }

        } catch (Exception e) // Catch exceptions
        {
            return getException(e);
        }
        return results;
    }

    /* Population 2 Report Continents */
    private static String getPopReport2() {
        String results = "";
        try {
            // SELECT STATEMENT to pull information required for the reports
            String strSelect = reportTotalPopulationContinents();

            // SQL Connect statements
            Statement stmt = con.createStatement();

            // Execute SQL statement
            stmt.executeQuery(strSelect);

            // This is the results set that is returned from the queries
            ResultSet set = stmt.executeQuery(strSelect);

            while (set.next()) {
                // New Instant of "World"
                World wd = new World();
                wd.totalPopulation = set.getString("TotalPopulation");
                wd.continent = set.getString("Continent");

                String newRES = wd.totalPopulation + "\t" + wd.continent + " \n";

                // Build Results
                results = results + newRES;
            }

            // Check we have Data
            if (!(results == null)) {

                // Display the results from the queries
                displayResultsWR("R2: Total Population Continent" + "\n" + results);
                return null;
            }

        } catch (Exception e) // Catch exceptions
        {
            return getException(e);
        }
        return results;
    }


}



