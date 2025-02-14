package com.mhwiki.toolkit.service;


import com.mhwiki.toolkit.domain.ItemTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private static final Logger log = LoggerFactory.getLogger(LogService.class);

    public void logSizes(int consumablesSize, int materialsSize, int ammunitionsSize, int accountItemsSize, int jewelsSize, int roomDecosSize) {
        log.info("There is {} elements in the {} category", consumablesSize, ItemTypeEnum.CONSUMABLE_TYPE);
        log.info("There is {} elements in the {} category", materialsSize, ItemTypeEnum.MATERIAL_TYPE);
        log.info("There is {} elements in the {} category", ammunitionsSize, ItemTypeEnum.AMMO_TYPE);
        log.info("There is {} elements in the {} category", accountItemsSize, ItemTypeEnum.ACCOUNT_ITEM_TYPE);
        log.info("There is {} elements in the {} category", jewelsSize, ItemTypeEnum.CONSUMABLE_TYPE);
        log.info("There is {} elements in the {} category", roomDecosSize, ItemTypeEnum.JEWEL_TYPE);
    }
}
