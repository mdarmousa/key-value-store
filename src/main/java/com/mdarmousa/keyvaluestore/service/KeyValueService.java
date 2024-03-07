package com.mdarmousa.keyvaluestore.service;

import com.mdarmousa.keyvaluestore.models.KeyValue;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KeyValueService {
    HashMap<String, String> keyValueStore = new HashMap<>();

    public KeyValue save(KeyValue keyValue){
        keyValueStore.put(keyValue.key(), keyValue.value());
        return keyValue;
    }
}
