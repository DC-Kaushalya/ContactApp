package com.example.contactslistapplication;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyApplication extends Application {

    public Map<String, Map<String, String>> outerMap = new HashMap<>();


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

    public void deletedData(String phone_number, String email) {
        String unique_id = phone_number + "_" + email;
        this.outerMap.remove(unique_id);
    }

    public Map<String, Map<String, String>> getOuterMap() {
        return outerMap;
    }
}
