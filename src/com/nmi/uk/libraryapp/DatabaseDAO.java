/*
 * Copyright (C) 2014 Darren
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.nmi.uk.libraryapp;

/**
 *
 * @author Darren
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class DatabaseDAO {

    private static String dbURL = "jdbc:derby://localhost:1527/libraryDB;create=false;user=admin;password=admin";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String books = "books";
    private static String author = "authors";
    private static String category = "category";
    private static String library = "library";
    private static String links = "book_details";
    private static String link_on = "book_name";

    public static void main(String[] args) {
        
        //FIXME replicate this sequence in calling class.
        // selecs statement would be used in the client to retireve data.
        // just spits to cosnole atm to demo
        
        createConnection();

        selectBooksByLibrary("Bangor");
        insertRecords("Dundee4", "Test6", "Alien 1`1", "Sci-fi");
        selectBooks();
        selectEverythingFromAll();
        shutdown();
    }

    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException except) {
            except.printStackTrace();
        }
    }
    private static void insertRecords(String lib_name, String auth_name, String book_name, String cat_name) {
        
        // FIXME: need method to check for duplicate entries in the parent table so id doesnt mess up the links update!!!
        try {
            stmt = conn.createStatement();
            //insedrt into library
            stmt.execute("insert into " + library + " values ('" + lib_name + "')");
            
            //stmt.execute("insert into " + library + " (select 'library_name' AS lib FROM library WHERE lib = '" + lib_name + "' HAVING count(*)=0)");
            
            // insert books
            stmt.execute("insert into " + books + " values ('" + book_name + "')");
            // insert author
            stmt.execute("insert into " + author + " values ('" + auth_name + "')");
            // insert category
            stmt.execute("insert into " + category + " values ('" + cat_name + "')");
            // update link table
            stmt.execute("insert into " + links + " values ('" + lib_name + "','" + auth_name + "','" + book_name + "','" + cat_name + "')");
            
            stmt.close();
        } catch (SQLException sqlExcept) {
            // FIXME: !!! there is on ON UPDATE DUPLICATE KEY in derby :/ so we have an issue wiht adding duplicated entries to the link table atm.
            sqlExcept.printStackTrace();
            
            
        }
    }

    private static void selectBooksByLibrary(String lib_name) {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(
                    "select book_name from " + books + " join " + links + " using (" + link_on + ") "
                    + "where library_name = '" + lib_name + "'"
            );
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            // print all data found for the qeury and column headers
            while (results.next()) {
                for (int i = 1; i <= numberCols; i++) {
                    System.out.println(results.getString(i));
                }
                System.out.println();
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
    
    private static void selectBooks() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(
                    "select book_name from " + books 
            );
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            // print all data found for the qeury and column headers
            while (results.next()) {
                for (int i = 1; i <= numberCols; i++) {
                    System.out.println(results.getString(i));
                }
                System.out.println();
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void selectAllBooksByAuthor(String auth_name) {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(
                    "select book_name from " + books + " join " + links + " using (" + link_on + ") "
                    + "where auth_name = '" + auth_name + "'"
            );
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-----------------------------------------------------------------------------------\"");

            // print all data found for the qeury and column headers
            while (results.next()) {
                for (int i = 1; i <= numberCols; i++) {
                    System.out.println(results.getString(i));
                }
                System.out.println();
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void selectEverythingFromAll() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(
                    "select * from " + books + " join " + links + " using (" + link_on + ") "
            );
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-----------------------------------------------------------------------------------");

            // print all data found for the qeury and column headers
            while (results.next()) {
                for (int i = 1; i <= numberCols; i++) {
                    System.out.print(results.getString(i) + "\t\t");

                }
                System.out.println();
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {

        }
    }
}
