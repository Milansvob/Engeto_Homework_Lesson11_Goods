package engeto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class Stock implements GoodsMethods {

    Item item = new Item();

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock",
            "StockItemsUser", "Karel01+");

    Statement statement = connection.createStatement();

    public Stock() throws SQLException {
    }

    @Override
    public Item loadItemById(Integer id) throws SQLException {
        ResultSet resultset = statement.executeQuery("SELECT * FROM stockitems WHERE id=" + id);
        while (resultset.next()) {
            item.setId(resultset.getInt("id"));
            item.setPartNo(resultset.getString("partNo"));
            item.setSerialNo(resultset.getString("serialNo"));
            item.setName(resultset.getString("name"));
            item.setDescription(resultset.getString("description"));
            item.setNumberInStock(resultset.getInt("numberInStock"));
            item.setPrice(resultset.getBigDecimal("price"));
            }
        return item;
    }

    @Override
    public void deleteAllOutOfStockItems() throws SQLException {
          statement.executeUpdate(("DELETE FROM stockitems WHERE numberInStock = 0"));
    }

    @Override
    public List<Item> loadAllAvailableItems() {
        return null;
    }

    @Override
    public void saveItem(Item item) {

    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) {

    }
}
