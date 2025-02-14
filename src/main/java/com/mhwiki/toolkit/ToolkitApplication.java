package com.mhwiki.toolkit;

import com.mhwiki.toolkit.service.ItemService;
import com.mhwiki.toolkit.service.domain.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class ToolkitApplication {

	private static final String CONSUMABLE_TYPE = "Item";
	private static final String MATERIAL_TYPE = "Material";
	private static final String AMMO_TYPE = "Ammo_or_Coating";
	private static final String ACCOUNT_ITEM_TYPE = "Account_Item";
	private static final String JEWEL_TYPE = "Jewel";
	private static final String ROOM_DECORATION_TYPE = "Room_Decoration";

	public static void main(String[] args) {
		// Get the service bean
		ApplicationContext context = SpringApplication.run(ToolkitApplication.class, args);
		ItemService itemService = context.getBean(ItemService.class);

		// Read the file and populate the list
		// Replace the value with the file in ressources/input you want to read
		// reducedItemList.json /  mhwi.items.json
		String filename = "reducedItemList.json";
		List<Item> items = itemService.getItemsFromJson(filename);

		// Sort the items based on their type
		List<Item> consumables = items.stream().filter(item -> StringUtils.equalsIgnoreCase(item.getType(), CONSUMABLE_TYPE)).toList();
		List<Item> materials = items.stream().filter(item -> StringUtils.equalsIgnoreCase(item.getType(), MATERIAL_TYPE)).toList();
		List<Item> ammunitions = items.stream().filter(item -> StringUtils.equalsIgnoreCase(item.getType(), AMMO_TYPE)).toList();
		List<Item> accountItems = items.stream().filter(item -> StringUtils.equalsIgnoreCase(item.getType(), ACCOUNT_ITEM_TYPE)).toList();
		List<Item> jewels = items.stream().filter(item -> StringUtils.equalsIgnoreCase(item.getType(), JEWEL_TYPE)).toList();
		List<Item> roomDecos = items.stream().filter(item -> StringUtils.equalsIgnoreCase(item.getType(), ROOM_DECORATION_TYPE)).toList();

	}
}