package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }


    @Test
    void testGetReport1()
    {
        // REPORT 1: All the countries in the World organised by largest population to smallest.
        String[] s = App.getReport1().split("\t", 10);
        assertEquals(s[6], "China");
    }

    @Test
    void testGetReport2()
    {
        // REPORT 2: list all the countries in a continent organised by largest population to smallest.
        String[] s = App.getReport2().split("\t", 10);
        assertEquals(s[6], "Russian Federation");
    }

    @Test
    void testGetReport3()
    {
        // REPORT 3: All the countries in a region organised by largest population to smallest.
        String[] s = App.getReport3().split("\t", 10);
        assertEquals(s[6], "Antarctica");
    }

    @Test
    void testGetReport4()
    {
        // REPORT 4: produce a report listing the top N populated countries in the World where N is provided by the user
        String[] s = App.getReport4(2).split("\t", 10);
        assertEquals(s[6], "China");
    }

    @Test
    void testGetReport5()
    {
        // REPORT 5 : The top N populated countries in a continent where N is provided by the user.
        String[] s = App.getReport5(5).split("\t", 10);
        System.out.println("Result: " + s[6]);
        assertEquals(s[6], "China");
    }

    @Test
    void testGetReport6()
    {
        // REPORT 6: produce a report listing the top N populated countries in a region where N is provided by the user
        String[] s = App.getReport6().split("\t", 10);
        assertEquals(s[6], "Turkey");
    }

    @Test
    void testGetReport7()
    {
        // REPORT 7 : All the cities in the World organised by largest population to smallest.
        String[] s = App.getReport7().split("\t", 10);
        assertEquals(s[6], "Maharashtra");
    }

    @Test
    void testGetReport8()
    {
        // REPORT 8: produce a report listing all the cities in a continent organised by largest population to smallest
        String[] s = App.getReport8().split("\t", 10);
        assertEquals(s[4], "\nCairo");
    }

    @Test
    void testGetReport9()
    {
        // REPORT 9: All the cities in a region organised by largest population to smallest.
        String[] s = App.getReport9().split("\t", 10);
        assertEquals(s[6], "New South Wales");
    }

    @Test
    void testGetReport10()
    {
        // REPORT 10: produce a report listing all the cities in a country organised by largest population to smallest
        String[] s = App.getReport10().split("\t", 10);
        assertEquals(s[4], "\nLondon");
    }

    @Test
    void testGetReport11()
    {
        // REPORT 11: All the cities in a district organised by largest population to smallest
        String[] s = App.getReport11().split("\t", 10);
        System.out.println("Result: " + s[4]);
        assertEquals(s[4], "\nTaiping");
    }

    @Test
    void testGetReport12()
    {
        // REPORT 12: produce a report listing the top N populated cities in the World where N is provided by the user
        String[] s = App.getReport12().split("\t", 10);
        assertEquals(s[4], "\nMumbai (Bombay)");
    }

    @Test
    void testGetReport13()
    {
        // REPORT 13: The top N populated cities in a continent where N is provided by the user.
        String[] s = App.getReport13(10).split("\t", 10);
        assertEquals(s[4], "\nMumbai (Bombay)");
    }

    @Test
    void testGetReport14()
    {
        // REPORT 14:  produce a report listing the top N populated cities in a region where N is provided by the user
        String[] s = App.getReport14().split("\t", 10);
        assertEquals(s[4], "\nNew York");
    }

    @Test
    void testGetReport15()
    {
        // REPORT 15: The top N populated cities in a country where N is provided by the user.
        String[] s = App.getReport15(10).split("\t", 10);
        assertEquals(s[4], "\nKabul");
    }

    @Test
    void testGetReport16()
    {
        // REPORT 16:  produce a report listing the top N populated cities in a district where N is provided by the user
        String[] s = App.getReport16().split("\t", 10);
        assertEquals(s[4], "\nCentro (Villahermosa)");
    }

    @Test
    void testGetReport17()
    {
        // REPORT 17: fixed All the capital cities in the World organised by largest population to smallest.
        String[] s = App.getReport17().split("\t", 2);
        assertEquals(s[0], "Seoul");
    }

    @Test
    void testGetReport18()
    {
        // REPORT 18: produce a report listing all the capital cities in a continent organised by largest population to smallest
        String[] s = App.getReport18().split("\t", 2);
        assertEquals(s[0], "Moscow");
    }

    @Test
    void testGetReport19()
    {
        // REPORT 19: : All the capital cities in a region organised by largest to smallest.
        String[] s = App.getReport19().split("\t", 2);
        assertEquals(s[0], "Canberra");
    }

    @Test
    void testGetReport20()
    {
        // REPORT 20: produce a report listing the top N populated capital cities in the World where N is provided by the user
        String[] s = App.getReport20().split("\t", 2);
        assertEquals(s[0], "Seoul");
    }

    @Test
    void testGetReport21()
    {
        // REPORT 21: As a service user I want to produce a report listing the top N populated
        // capital cities in a continent where N is provided by the user
        String[] s = App.getReport21(5).split("\t", 2);
        assertEquals(s[0], "Mumbai (Bombay)");
    }

    @Test
    void testGetReport22()
    {
        // REPORT 22: produce a report listing the top N populated capital cities in a region where N is provided by the user
        String[] s = App.getReport22().split("\t", 2);
        assertEquals(s[0], "La Habana");
    }

    @Test
    void testGetReport23()
    {
        // REPORT 23: As a service user I want to produce a report listing the population of people,
        // people living in cities, and people not living in cities in each continent
        String[] s = App.getReport23().split("\t", 2);
        assertEquals(s[0], "Asia");
    }

    @Test
    void testGetReport24()
    {
        // REPORT 24: produce a report listing the population of people, people living in cities, and people not living in cities in each region
        String[] s = App.getReport24().split("\t", 2);
        assertEquals(s[0], "Southern and Central Asia");
    }

    @Test
    void testGetReport25()
    {
        // 25. As a service user I want to produce a report listing the population of people,
        // people living in cities, and people not living in cities in each country
        String[] s = App.getReport25().split("\t", 2);
//        System.out.println("Result: " + s[0]);
        assertEquals(s[0], "Afghanistan");
    }

    @Test
    void testGetPopReport1()
    {
        String s = App.getPopReport1();
        assertEquals(s, "6078749450 " + "\n");
    }

    @Test
    void testGetPopReport2()
    {
        String[] s = App.getPopReport2().split("\n", 4);
        assertEquals(s[0], "482993000\tNorth America ");
    }

    @Test
    void testGetPopReport3()
    {
        String[] s = App.getPopReport3().split("\n", 4);
        assertEquals(s[0], "38140000\tCaribbean ");
    }

    @Test
    void testGetPopReport4()
    {
        String[] s = App.getPopReport4().split("\n", 4);
        assertEquals(s[0], "103000\tAruba ");
    }

    @Test
    void testGetPopReport5()
    {
        String[] s = App.getPopReport5().split("\n", 4);
        assertEquals(s[0], "1780000\tKabol ");
    }

    @Test
    void testGetPopReport6()
    {
        String[] s = App.getPopReport6().split("\n", 4);
        assertEquals(s[0], "1780000\tKabul ");
    }

    @Test
    void testGetPopReport7()
    {
        String[] s = App.getPopReport7().split("\n", 4);
        assertEquals(s[0], "United States\tEnglish\t86.2 ");
    }

    @Test
    void testGetPopReportStats()
    {
        String[] s = App.getPopReportStats().split("\t", 10);
//        System.out.println("Result: " + s[8]);
        assertEquals(s[8], "Southern and Central Asia");
    }
    
}