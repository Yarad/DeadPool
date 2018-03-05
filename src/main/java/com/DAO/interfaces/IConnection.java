package com.DAO.interfaces;


import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;

/*Абстрактный класс предназначен для классов-наследников, представляющих
некоторое подключение к БД. Например SqlConnection.
Объекты классов-наследников будут использованы,
для того чтобы достать данные из  БД*/

public interface IConnection {
    boolean connect();

    void disconnect();

    PreparedStatement prepareStatement(String sql);

    List<HashMap<String, Object>> queryFind(PreparedStatement queryPrepared);
    boolean queryDataEdit(PreparedStatement queryPrepared);
    int getLastAddedId(PreparedStatement preparedStatement);
}
