package com.DAO;

import com.DAO.interfaces.IConnection;

/*Классы-наследники этого класса соответствуют
сущностям БД. Суть состоит в том, чтобы абстрагировать
БД от самих объектов, коим и так не будет числа*/
public abstract class DAO {

    //ссылка на используемое подключение
    protected IConnection currConnection;

    DAO()
    {

    }

    public Boolean setConnectionToUse(IConnection connectionToUse)
    {
        if(connectionToUse == null) {
            return false;
        }
        else {
            currConnection = connectionToUse;
            return currConnection.connect();
            //return true;
        }
    }

    public void finalize()
    {
        //currConnection.disconnect();
    }

}
