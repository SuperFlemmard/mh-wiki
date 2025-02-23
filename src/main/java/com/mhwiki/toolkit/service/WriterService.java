package com.mhwiki.toolkit.service;


import com.mhwiki.toolkit.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WriterService {

    // Wiki text syntax and genereic text
    private static final String FILE_HEADER = "{{#seo:\n" +
            "|title=Items in Monster Hunter World : Iceborne\n" +
            "|description=A list of all gatherable items in Monster Hunter World: Iceborne.\n" +
            "|image=5thGen-Question Mark Icon White.png\n" +
            "}}\n" +
            "{{GenericNav|MHWI}}\n" +
            "The following is a list of all items that appear in [[Monster Hunter World: Iceborne]]\n";

    private static final String TOC = "__TOC__\n";

    private static final String TABLE_START = "{| class=\"wikitable sortable mw-collapsible\" style=\"width: 100%\" \"\n" +
            "! Name || Rarity || Buy Price || Sell Price || Carry || Description \n";

    private static final String TABLE_END = "|}\n";

    // Logger
    private static final Logger log = LoggerFactory.getLogger(WriterService.class);

    /**
     * Takes the lists of items and write them to a .txt file understandable by MediaWiki
     */
    public void writeToFile(List<Item> consumables, List<Item> materials, List<Item> ammunitions, List<Item> accountItems, List<Item> jewels, List<Item> roomDecorations) throws IOException {

        // Naming the file with the current date
        LocalDateTime now = LocalDateTime.now();
        String fileName = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth() + "--" + now.getHour() +now.getMinute() + now.getSecond()  + ".txt";
        log.info("Writing to {} file", fileName);

        // Create the file with the headers and different lists of items
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.write(FILE_HEADER);
        writer.write(TOC);

        writer.write("== Consumables ==\n");
        writer.write(TABLE_START);
        writeItems(consumables, writer);
        writer.write(TABLE_END);

        writer.write("== Materials ==\n");
        writer.write(TABLE_START);
        writeItems(materials, writer);
        writer.write(TABLE_END);

        writer.write("== Ammo / Coatings ==\n");
        writer.write(TABLE_START);
        writeItems(ammunitions, writer);
        writer.write(TABLE_END);

        writer.write("== Account Items ==\n");
        writer.write(TABLE_START);
        writeItems(accountItems, writer);
        writer.write(TABLE_END);

        writer.close();
    }

    /**
     * Takes a list of item (by category) then generates the wiki text syntax
     */
    private void writeItems(List<Item> items, BufferedWriter writer) throws IOException {
        for (Item item : items) {
            String text = "";
            text += "|-\n";
            text += "| {{MHWIItemLink|" + item.getName() + "|" + item.getWikiIconName() + "|" + item.getWikiIconColor() + "}}\n";
            text += "| align=\"center\" |" + item.getRarity() + "\n";
            text += "| align=\"center\" |" + getPrice(item.getBuyPrice()) + "\n";
            text += "| align=\"center\" |" + getPrice(item.getSellPrice()) + "\n";
            text += "| align=\"center\" |" + item.getCarryLimit() + "\n";
            text += "| " + item.getDescription() + "\n";

            writer.write(text);
        }
    }

    /**
     * Format the buying and selling price of items
     */
    private String getPrice(int price) {
        return price == 0 ? "-" : price + "z";
    }
}