package com.example.contactslistapplication;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// MyApplication.java (extends Application)
public class MyApplication extends Application {
//    private String sharedData;
//    public List<Contact> contactsList = new ArrayList<>();

    public Map<String, Map<String, String>> outerMap = new HashMap<>();

//    private Map<String, String> details = new HashMap<>();

    public List<Contact> contactsList = new ArrayList<>();

//    public Map getSharedData() {
//        return outerMap;
//    }

    public void setSharedData(String name, String phone_number, String email) {
        Map<String, Map<String, String>> outerMapNew = new HashMap<>();
        Map<String, String> details = new HashMap<>();
        String unique_id = phone_number + "_" + email;
        details.put("name", name);
        details.put("phone_number", phone_number);
        details.put("email", email);
        outerMapNew.put(unique_id, details);

        this.outerMap.put(unique_id, details);
    }

    public Map<String, Map<String, String>> getOuterMap() {
        return outerMap;
    }
}
