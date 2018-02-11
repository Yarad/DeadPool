package com.logic;


import java.util.HashMap;

public class ProjectFunctions {
    public static boolean ifDbObjectContainsKey(HashMap<String, Object> dbRetObject, String key) {
        return dbRetObject.containsKey(key) && dbRetObject.get(key) != null;
    }
}
