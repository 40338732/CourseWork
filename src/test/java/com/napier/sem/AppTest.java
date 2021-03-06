package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

public class AppTest
{
    static App app;
    static Connection con = null;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    public void printSalaries(ArrayList<Employee> employees)
    {
        // Check employees is not null
        if (employees == null)
        {
           // System.out.println("No employees");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees)
        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }


    @Test
    void testReport4LessThanZero()
    {
        app.getReport4(-2);
    }

    @Test
    void testPrintCountries() {

        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.name = "United Korea";
        country.continent = "Asia";
        country.population = 7088300;

        countries.add(country);
        app.printCountries(countries);
    }

    @Test
    void testPrintCountriesNull()
    {
        app.printCountries(null);
    }

    @Test
    void testPrintCountriesEmpty()
    {
        ArrayList<Country> countries = new ArrayList<>();
        app.printCountries(countries);
    }

    @Test
    void testPrintCountriesWithNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        app.printCountries(countries);
    }


    @Test
    void testReport8ForConsistentOutput()
    {

    }

    @Test
    void testReportsReturnDifferentOutput()
    {

    }


//    @Test
//    void printSalariesTestNull()
//    {
//      //  app.printSalaries(null);
//    }
//
//    @Test
//    void printSalariesTestEmpty()
//    {
//        ArrayList<Employee> employess = new ArrayList<Employee>();
//     //  app.printSalaries(employess);
//    }
//
//    // Employees Contains null
//    @Test
//    void printSalariesTestContainsNull()
//    {
//        ArrayList<Employee> employess = new ArrayList<Employee>();
//        employess.add(null);
//     //   app.printSalaries(employess);
//    }
//
//    // Employee Contains All Non-null
//    @Test
//    void printSalaries()
//    {
//        ArrayList<Employee> employees = new ArrayList<Employee>();
//        Employee emp = new Employee();
//        emp.emp_no = 1;
//        emp.first_name = "Kevin";
//        emp.last_name = "Chalmers";
//        emp.title = "Engineer";
//        emp.salary = 55000;
//        employees.add(emp);
//     //   app.printSalaries(employees);
//    }
//
//    @Test
//    void displayEmployee()
//    {
//
//        //Create single "Employee"
//        //Set the variables
//        // and pass it to Display Employee
//        ArrayList<Employee> employees = new ArrayList<Employee>();
//
//        Employee emp = new Employee();
//        emp.emp_no = 1;
//        emp.first_name = "Kevin";
//        emp.last_name = "Chalmers";
//        emp.title = "Engineer";
//        emp.salary = 55000;
//        employees.add(emp);
//
//        //No ID Supplied
//     //   app.displayEmployee(null);
//        //ID Supplied
//        //app.displayEmployee(emp);
//
//
//    }
}
