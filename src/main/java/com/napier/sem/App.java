package com.napier.sem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class App
{
    /**      * Connection to MySQL database.      */

    private Connection con = null;

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
                con = DriverManager.getConnection("jdbc:MySQL://db:3306/employees?useSSL=false","root", "example");
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
         * */

        public void disconnect() {
            if (con != null)
            {
                try
                {
                    // Close connection
                    con.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error closing connection to database");
                }
            }
        }








 public static void main(String[] args) {
     // Create new Application
     App a = new App();
     System.out.println("version 2");
     a.connect();

     // Get Employee
    // Employee emp =
             a.getEmployee2(255530);

     // Get saleries
     //a.displayEmployeeSalaries(emp);


     // Display results
     //a.displayEmployee(emp);

     // Disconnect from database
     a.disconnect();
 }



    public void displayEmployee(Employee emp)
    {

        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
    }


    public void displayEmployeeSalaries( )
    {

    }
    public Employee getEmployee2(int ID)
    {
        System.out.println("here");
        try {
            // Create string for SQL statement
            /*   ISSUE 1
            String strSelect = "SELECT e.emp_no, e.first_name, e.last_name,  t.title, t.from_date , t.to_date , s.salary "
                    + " FROM employees e "
                    + " JOIN titles t on t.emp_no = e.emp_no"
                    + " JOIN salaries s on s.emp_no = e.emp_no and s.from_date =  t.from_date "
                    + " WHERE   t.to_date = '9999-01-01' ";
             */
            String strSelect = "SELECT e.emp_no, e.first_name, e.last_name,  t.title, t.from_date , t.to_date , s.salary "
                    + " FROM employees e "
                    + " JOIN titles t on t.emp_no = e.emp_no"
                    + " JOIN salaries s on s.emp_no = e.emp_no and s.from_date =  t.from_date "
                    + " WHERE   t.to_date = '9999-01-01' ";
            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            System.out.println(
                    "Emp No:" +"\t" +" FirstName:" +"\t" + " Surname:" +"\t"  + "title" + "\t" + "from Date" + "\t" + "to Date"+ "\t" + "salary");
            while (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.fromdate = rset.getDate("from_date");
                emp.todate = rset.getDate("to_date");
                emp.salary = rset.getInt("salary");


                System.out.println(
                     emp.emp_no  +"\t" +
                        emp.first_name + "\t" + emp.last_name +"\t" + emp.title + "\t" + emp.fromdate + "\t" + emp.todate + "\t" + emp.salary+ "\n");

            }
            return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public Employee getEmployee(int ID)
    {
        System.out.println("here");
        try {

            // Statement stmt = con.createStatement();
            /*
            String strSelect = "SELECT e.emp_no, e.first_name, e.last_name, s.salary  "
                    + "FROM employees e, salaries s "
                    + "WHERE e.emp_no = s.emp_no";


            String strSelect = "SELECT emp_no, first_name, last_name "
                    + "FROM employees "
                    + "WHERE emp_no = " + ID;

                String strSelect = "SELECT e.emp_no, e.first_name, e.last_name, s.salary  "
                    + "FROM employees e JOIN salaries s  "
                    + "ON e.emp_no = s.emp_no ";
            */

            // Create string for SQL statement
            String strSelect = "SELECT emp_no, first_name, last_name "
                    + "FROM employees ";
                //    + "WHERE emp_no = " + ID;

                   //     FROM tutorials_tbl a LEFT JOIN tcount_tbl b
                  //  -> ON a.tutorial_author = b.tutorial_author;
                   // + "WHERE e.emp_no = s.emp_no";

            Statement stmt = con.createStatement();
            // Statement conn = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("Count " + rset);
            // Return new employee if valid.
            // Check one is returned

            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
               // emp.salary = rset.getInt( "salary");
                System.out.println(
                emp.emp_no + " " + emp.first_name + " " + emp.last_name +" " + emp.title +" "+ "Salary:" + emp.salary + "\n");
                return null;
            }
            else
            {
                return null;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }


}



