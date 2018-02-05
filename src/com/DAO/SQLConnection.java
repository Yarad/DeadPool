package com.DAO;

import com.DAO.interfaces.IConnection;

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

    public List<HashMap<String, Object>> queryFind(String queryString) {

        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try {
            // opening database connection to MySQL server

            // executing SELECT query
            rs = stmt.executeQuery(queryString);

            //надо ещё разобраться, как эта байда работает
            int amountOfColumns = rs.getMetaData().getColumnCount();
            String currColumnName;

            while (rs.next()) {
                HashMap<String, Object> columnList = new HashMap<String, Object>();
                for (int i = 1; i <= amountOfColumns; i++) {
                    currColumnName = rs.getMetaData().getColumnName(i);
                    columnList.put(currColumnName, rs.getObject(i));
                }
                list.add(columnList);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything  */}
        }
        return list;
    }

    public boolean queryDataEdit(String queryString) {
        boolean retValue = false;
        try {
            // opening database connection to MySQL server

            // executing SELECT query
            retValue = stmt.execute(queryString, Statement.RETURN_GENERATED_KEYS);

            //надо ещё разобраться, как эта байда работает

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
        }
        return retValue;
    }

    public int getLastAddedId() {
        int ret = -1;
        try {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                ret = rs.getInt(1);
        } catch (Exception e) {
        }
        return ret;
    }

    public boolean connect() {
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

    public void disconnect() {
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
