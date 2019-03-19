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
        String[] s = App.getReport2().split("\t", 2);
        //System.out.println("Results" + s[0]);
        assertEquals(s[0], "Russian Federation");
//
    }
}