package com.mhwiki.toolkit.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhwiki.toolkit.service.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ItemService {

    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Load the file and map the json values to a list of Items
     */
    public List<Item> getItemsFromJson(String filename) {
        String filePath = "/input/" + filename;

        try {
            InputStream inputStream = new ClassPathResource(filePath).getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read items from JSON file", e);
        }
    }
}
