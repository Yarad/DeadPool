package com.logic;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectFunctions {
    public static boolean ifDbObjectContainsKey(HashMap<String, Object> dbRetObject, String key) {
        return dbRetObject.containsKey(key) && dbRetObject.get(key) != null;
    }

    //пытается заполнить объект с использованием массива, полученного из БД
    //ищет setters с использованием рефлексии
    //возвращает часть исходного массива, setter-ы для которого не были найдены

    public static HashMap<String, Object> tryFillObjectByDbArray(Object object, HashMap<String, Object> dbArray) {
        List<Method> existingSetters = getSetters(object.getClass().getMethods());
        HashMap<String, Object> avoidedElementsOfArray = new HashMap<String, Object>();

        for (Map.Entry<String, Object> item : dbArray.entrySet()) {
            String tempStr = item.getKey().toLowerCase().replaceAll("_", "");
            Method methodToRun = null;

            for (int i = 0; i < existingSetters.size(); i++)
                if (existingSetters.get(i).getName().toLowerCase().equals("set".concat(tempStr)) /* && existingSetters.get(i).getGenericParameterTypes()[0] == item.getClass()*/) {
                    //TODO: починить LocalDate initialization
                    methodToRun = existingSetters.get(i);
                    break;
                }

            if (methodToRun != null) {
                try {

                    methodToRun.invoke(object, item.getValue());
                } catch (Exception e) {
                    try {

                        if (item.getValue() instanceof Time) {
                            methodToRun.invoke(object, LocalTime.parse(item.getValue().toString(), ProjectConstants.myTimeFormatter));
                        } else if (item.getValue() instanceof Date) {
                            methodToRun.invoke(object, LocalDate.parse(item.getValue().toString(), ProjectConstants.myDateFormatter));
                        } else if (item.getValue() instanceof Timestamp) {
                            methodToRun.invoke(object, LocalDateTime.parse(item.getValue().toString(), ProjectConstants.myDateTimeFormatter));
                        }

                    } catch (Exception e2) {
                        avoidedElementsOfArray.put(item.getKey(), item.getValue());
                    }
                }
            } else
                avoidedElementsOfArray.put(item.getKey(), item.getValue());

            //TODO:  object.getClass().getMethod("set".concat(convertDbNameToSetterPostfix(item.getKey())));
        }
        return avoidedElementsOfArray;
    }

    private static List<Method> getSetters(Method[] methodsToFilter) {
        List<Method> retGetters = new ArrayList<Method>();
        for (int i = 0; i < methodsToFilter.length; i++)
            if (methodsToFilter[i].getName().substring(0, 3).equals("set"))
                retGetters.add(methodsToFilter[i]);
        return retGetters;
    }
}
