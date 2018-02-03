package com.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class SQLConnection implements IConnection {

    private String url = "jdbc:mysql://localhost:3306/test";
    private String user = "root";
    private String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    SQLConnection(String url, String userName,String password)
    {
        this.url = url;
        this.user = userName;
        this.password = password;
    }

    public String query(String queryString) {
        try {
            // opening database connection to MySQL server

            // executing SELECT query
            rs = stmt.executeQuery(queryString);

            //надо ещё разобраться, как эта байда работает
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything  */}
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything  */}
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything  */}
        }
        return "";
    }

    public boolean Connect() {
        boolean retValue = true;

        try {
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            retValue = false;
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { retValue = false;}
            try {
                stmt.close();
            } catch (SQLException se) { retValue = false;}
        }
        return retValue;
    }
}
