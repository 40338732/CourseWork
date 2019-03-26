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
    void testGetReport2()
    {
        // REPORT 2: list all the countries in a continent organised by largest population to smallest.
        String[] s = App.getReport2().split("\t", 10);
        assertEquals(s[6], "Russian Federation");
    }

    @Test
    void testGetReport4()
    {
        // REPORT 4: produce a report listing the top N populated countries in the World where N is provided by the user
        String[] s = App.getReport4(2).split("\t", 10);
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
    void testGetReport8()
    {
        // REPORT 8: produce a report listing all the cities in a continent organised by largest population to smallest
        String[] s = App.getReport8().split("\t", 10);
        assertEquals(s[4], "\nCairo");
    }

    @Test
    void testGetReport10()
    {
        // REPORT 10: produce a report listing all the cities in a country organised by largest population to smallest
        String[] s = App.getReport10().split("\t", 10);
        assertEquals(s[4], "\nLondon");
    }

    @Test
    void testGetReport12()
    {
        // REPORT 12: produce a report listing the top N populated cities in the World where N is provided by the user
        String[] s = App.getReport12().split("\t", 10);
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
    void testGetReport16()
    {
        // REPORT 16:  produce a report listing the top N populated cities in a district where N is provided by the user
        String[] s = App.getReport16().split("\t", 10);
        assertEquals(s[4], "\nCentro (Villahermosa)");
    }

    @Test
    void testGetReport18()
    {
        // REPORT 18: produce a report listing all the capital cities in a continent organised by largest population to smallest
        String[] s = App.getReport18().split("\t", 2);
        assertEquals(s[0], "Moscow");
    }

    @Test
    void testGetReport20()
    {
        // REPORT 20: produce a report listing the top N populated capital cities in the World where N is provided by the user
        String[] s = App.getReport20().split("\t", 2);
        assertEquals(s[0], "Seoul");
    }

    @Test
    void testGetReport22()
    {
        // REPORT 22: produce a report listing the top N populated capital cities in a region where N is provided by the user
        String[] s = App.getReport22().split("\t", 2);
        assertEquals(s[0], "La Habana");
    }

    @Test
    void testGetReport24()
    {
        // REPORT 22: produce a report listing the top N populated capital cities in a region where N is provided by the user
        String[] s = App.getReport24().split("\t", 2);
        //System.out.println("Result: " + s[0]);
        assertEquals(s[0], "Southern and Central Asia");
    }

}