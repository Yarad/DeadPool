package com.DAO;

import javax.management.Query;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLConnection implements IConnection {

    private String url = "jdbc:mysql://localhost:3306/DeadPoolDB";
    private String user = "root";
    private String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public SQLConnection() {/*оставлю пустой конструктор, дабы можно было оставить изначально инициализированные значения*/}

    public SQLConnection(String url, String userName, String password) {
        this.url = url;
        this.user = userName;
        this.password = password;
    }

    public List<List<Object>> query(String queryString) {

        List<List<Object>> list = new ArrayList<List<Object>>();
        try {
            // opening database connection to MySQL server

            // executing SELECT query
            rs = stmt.executeQuery(queryString);

            //надо ещё разобраться, как эта байда работает
            int amountOfColumns = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                List<Object> columnList = new ArrayList<Object>();
                for(int i=1;i<=amountOfColumns;i++)
                    columnList.add(rs.getObject(i));
                list.add(columnList);
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
        return list;
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
        }
        return retValue;
    }

    public void Disconnect() {
        //close connection ,stmt and resultset here
        try {
            con.close();
        } catch (Exception se) {
        }
        try {
            stmt.close();
        } catch (Exception se) {
        }
    }
}
