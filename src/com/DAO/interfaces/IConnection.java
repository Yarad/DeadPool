package com.DAO.interfaces;

import java.util.HashMap;
import java.util.List;

/*Абстрактный класс предназначен для классов-наследников, представляющих
некоторое подключение к БД. Например SqlConnection.
Объекты классов-наследников будут использованы,
для того чтобы достать данные из  БД*/

public interface IConnection {
    boolean connect();

    void disconnect();

    List<HashMap<String, Object>> queryFind(String queryString);
    boolean queryDataEdit(String queryString);
    int getLastAddedId();
}
