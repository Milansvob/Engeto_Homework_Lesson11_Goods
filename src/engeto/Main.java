package engeto;

import java.math.BigDecimal;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Stock stock = new Stock();

        // Load demanded item according id
        System.out.println(stock.loadItemById(3));

        // Delete item with numberInStock = 0
        stock.deleteAllOutOfStockItems();

        System.out.println();

        // Load all available items
        for(Item a : stock.loadAllAvailableItems()){
            System.out.println(a);
        }

        // Add items  (numberInStock = 0 for both items, therefore two previous added rows are deleted in method deleteAllOutOfStockItems())
        Item itemScrew = new Item("00PSG012", "S000012", "Screw", "M5x20", 0, BigDecimal.valueOf(1.25));
        Item itemNut = new Item("00PSG013", "S000013", "Nut", "M5", 0, BigDecimal.valueOf(0.25));
        stock.saveItem(itemScrew);
        stock.saveItem(itemNut);

        //Update price of item selected according id
        stock.updatePrice(1,BigDecimal.valueOf(40.55));





        }
}
