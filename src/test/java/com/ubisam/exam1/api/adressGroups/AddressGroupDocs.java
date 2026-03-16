package com.ubisam.exam1.api.adressGroups;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class AddressGroupDocs extends MockMvcRestDocs {

    public Map<String, Object> newEntity(String name) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("name", name);
        return entity;
    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String name) {
        entity.put("name", name);
        return entity;
    }

}
