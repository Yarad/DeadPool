package com.DAO;

import com.DAO.interfaces.IConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLConnection implements IConnection {
    static Logger log = Logger.getLogger(SQLConnection.class.getName());

    private String url = "jdbc:mysql://localhost:3306/DeadPoolDB";
    private String user = "root";
    private String password = "uthfkmn";
    //private String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private static SQLConnection instance;

    private SQLConnection() {/*оставлю пустой конструктор, дабы можно было оставить изначально инициализированные значения*/}

    @Override
    protected void finalize() throws Throwable {
        disconnect();
    }

    public static SQLConnection getInstance() {
        if (instance == null) {
            instance = new SQLConnection();
            instance.connect();
        }
        return instance;
    }

    public List<HashMap<String, Object>> queryFind(PreparedStatement queryPrepared) {

        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try {
            // opening database connection to MySQL server

            // executing SELECT query
            rs = queryPrepared.executeQuery();

            //надо ещё разобраться, как эта байда работает
            int amountOfColumns = rs.getMetaData().getColumnCount();
            String currColumnName;


            while (rs.next()) {
                if (rs.isClosed() || rs.wasNull())
                    continue;

                HashMap<String, Object> columnList = new HashMap<String, Object>();
                for (int i = 1; i <= amountOfColumns; i++) {
                    currColumnName = rs.getMetaData().getColumnName(i);
                    columnList.put(currColumnName, rs.getObject(i));
                }
                list.add(columnList);
            }

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //NOT !!! close connection ,stmt and resultset here
            //Всё ломается, если закрывать ResultSet, т.к. его нельзя использовать повторно
            //try {
            //    if (rs != null)
            //        rs.close();
            //} catch (SQLException se) { /*can't do anything  */}
        }
        return list;
    }

    public boolean queryDataEdit(PreparedStatement queryPrepared) {
        boolean retValue = false;
        try {
            // opening database connection to MySQL server

            // executing SELECT query
            //retValue = stmt.execute(queryString);
            //Statement.RETURN_GENERATED_KEYS задётся в preparStatement
            retValue = queryPrepared.executeUpdate() != 0;
            //надо ещё разобраться, как эта байда работает

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
        }
        return retValue;
    }

    public int getLastAddedId(PreparedStatement preparedStatement) {
        int ret = -1;
        try {
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                ret = rs.getInt(1);
        } catch (Exception e) {
            log.error(e.toString());
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
            log.error(e.toString());
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

    @Override
    public PreparedStatement prepareStatement(String sql) {
        PreparedStatement retValue = null;
        try {
            //в этом месте я немножко схалявил
            //по-хорошему Statement.RETURN_GENERATED_KEYS должен быть вариативным
            //но примем, будто он нужен всегда
            retValue = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            log.error(e.toString());
        }

        return retValue;
    }
}