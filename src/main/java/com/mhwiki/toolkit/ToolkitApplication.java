package com.mhwiki.toolkit;

import com.mhwiki.toolkit.domain.ItemTypeEnum;
import com.mhwiki.toolkit.service.ItemService;
import com.mhwiki.toolkit.domain.Item;
import com.mhwiki.toolkit.service.LogService;
import com.mhwiki.toolkit.service.WriterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ToolkitApplication {

	private static final Logger log = LoggerFactory.getLogger(ItemService.class);

	public static void main(String[] args) throws IOException {

		// TODO At some point with the project evolution a refactor will be necessary

		// Get the service beans
		ApplicationContext context = SpringApplication.run(ToolkitApplication.class, args);
		ItemService itemService = context.getBean(ItemService.class);
		LogService logService = context.getBean(LogService.class);
		WriterService writerService = context.getBean(WriterService.class);

		// Reads the file and populate the list
		// Replaces the value with the file in ressources/input you want to read
		String filename = "mhwi.items.json";
		List<Item> items = itemService.getItemsFromJson(filename);
		log.info("The file has {} items", items.size());

		// Sorts the items based on their type and filter them
		List<Item> consumables = items.stream()
				.filter(item ->
						StringUtils.equals(item.getType(), ItemTypeEnum.CONSUMABLE_TYPE)
						&& !StringUtils.equals(item.getName(), "Unavailable")
						&& !StringUtils.equals(item.getDescription(), "Signal to your Palico to use their Palico Gadget.")
						&& !StringUtils.equals(item.getDescription(), "Unavailable")
				).toList();

		List<Item> materials = items.stream()
				.filter(item ->
						StringUtils.equals(item.getType(), ItemTypeEnum.MATERIAL_TYPE)
						&& !StringUtils.equals(item.getName(), "Unavailable")
						&& !StringUtils.equals(item.getName(), "HARDUMMY")
						&& !StringUtils.equals(item.getDescription(), "Unavailable")
				).toList();

		List<Item> ammunitions = items.stream()
				.filter(item ->
						StringUtils.equals(item.getType(), ItemTypeEnum.AMMO_TYPE)
						&& !StringUtils.equals(item.getDescription(), "Unavailable")
				).toList();

		List<Item> accountItems = items.stream()
				.filter(item ->
						StringUtils.equals(item.getType(), ItemTypeEnum.ACCOUNT_ITEM_TYPE)
						&& !StringUtils.equals(item.getName(), "Unavailable")
						&& !StringUtils.equals(item.getDescription(), "Unavailable")
				).toList();

		List<Item> jewels = items.stream()
				.filter(item ->
						StringUtils.equals(item.getType(), ItemTypeEnum.JEWEL_TYPE)
						&& !StringUtils.equals(item.getName(), "Unavailable")
						&& !StringUtils.equals(item.getDescription(), "Unavailable")
				).toList();

		List<Item> roomDecos = items.stream()
				.filter(item ->
						StringUtils.equals(item.getType(), ItemTypeEnum.ROOM_DECORATION_TYPE)
						&& !StringUtils.equals(item.getName(), "Unavailable")
						&& !StringUtils.equals(item.getDescription(), "Unavailable")
				).toList();

		// Log some info
		logService.logSizes(consumables.size(), materials.size(), ammunitions.size(), accountItems.size(), jewels.size(), roomDecos.size());

		// Write to txt file
		writerService.writeToFile(consumables, materials, ammunitions, accountItems, jewels, roomDecos);
	}
}