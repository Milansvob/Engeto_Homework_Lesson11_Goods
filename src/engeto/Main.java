package engeto;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Stock stock = new Stock();

        System.out.println(stock.loadItemById(1));

        stock.deleteAllOutOfStockItems();





        }
}
