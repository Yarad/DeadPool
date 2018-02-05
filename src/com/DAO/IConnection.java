package com.DAO;

import com.sun.org.apache.xpath.internal.operations.Bool;

/*Абстрактный класс предназначен для классов-наследников, представляющих
некоторое подключение к БД. Например SqlConnection.
Объекты классов-наследников будут использованы,
для того чтобы достать данные из  БД*/
public interface IConnection {
    boolean Connect();
    void Disconnect();
}
