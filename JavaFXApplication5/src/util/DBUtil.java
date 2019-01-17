/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mysql.jdbc.Connection;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ACER
 */
public class DBUtil {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static final String connStr = "jdbc:mysql://localhost/kolekcja_gier";

    //otwieranie połączenia
    public static void dbConnect() throws SQLException, ClassNotFoundException {    
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Gdzie twój MySQL JDBC Driver");
            e.printStackTrace();
            throw e;
        }
        System.out.println("JDBC zostało załączone");
        try {
            connection = (Connection) DriverManager.getConnection(connStr, "", "");
        } catch (SQLException e) {
            System.out.println("Nie dało się połaczyć" + e);
        }

    }

    //kończenie połączenia
    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //do operacji wstawiania/usuwania/updateowania
    public static void dbExcecuteQuery(String sglStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeUpdate(sglStmt);
        } catch (SQLException e) {
            System.out.println("Problem nastąpił przy dbExecuteQuery" + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
    //to służy do pobierania rekordów z bazy danych

    public static ResultSet dbExecute(String sqlQuery) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;

        try {
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w dbExcecute operacji" + e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
}
