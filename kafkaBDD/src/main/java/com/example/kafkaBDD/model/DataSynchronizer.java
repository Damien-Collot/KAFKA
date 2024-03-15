package com.example.kafkaBDD.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSynchronizer {

    private static final Object lock = new Object();
    private static Map<String, String> dataMap = new HashMap<>();

    public static void addData(String requestKey, String data) {
        synchronized (lock) {
            dataMap.put(requestKey, data);
        }
    }

    public static Map<String, String> getData() {
        synchronized (lock) {
            return new HashMap<>(dataMap);
        }
    }

    public static String getByKey(String requestKey) {
        synchronized (lock) {
            return dataMap.get(requestKey);
        }
    }

}