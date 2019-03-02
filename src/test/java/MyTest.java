import com.napier.sem.world;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


class MyTest
{

    private static Connection con = null;
    @Test
    void unitTest()
    {
        assertEquals(5, 5);
    }

    @Test
    void unitTest3()
    {
        assertEquals(5, 5, "Messages are equal");
    }

    @Test
    void unitTest4()
    {
        assertEquals(5.0, 5.01, 0.02);
    }

    @Test
    void unitTest5()
    {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertArrayEquals(a, b);
    }

    @Test
    void unitTest6()
    {
        assertTrue(5 == 5);
    }

    @Test
    void unitTest7()
    {
        assertFalse(5 == 4);
    }

    @Test
    void unitTest8()
    {
        assertNull(null);
    }

    @Test
    void unitTest9()
    {
        assertNotNull("Hello");
    }

    @Test
    void unitTest11()
    {
        assertThrows(NullPointerException.class, this::throwsException);
    }
    @Test
    void throwsException() throws NullPointerException
    {
        throw new NullPointerException();
    }

    @Test
    void unitTest10()
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
            //return null;
        }
        //return results;
    }


}