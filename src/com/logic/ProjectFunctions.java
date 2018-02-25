package com.logic;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
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
                if (existingSetters.get(i).getName().toLowerCase().equals("set".concat(tempStr))) {
                    methodToRun = existingSetters.get(i);
                    break;
                }

            if (methodToRun != null) {
                try {
                    Type setValueType = methodToRun.getGenericParameterTypes()[0];

                    methodToRun.invoke(object,  item.getValue());
                } catch (Exception e) {
                    avoidedElementsOfArray.put(item.getKey(), item.getValue());
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
