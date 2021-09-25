package engeto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Stock implements GoodsMethods {

    private static final String INSERT_ITEM = "INSERT INTO stockitems (partNo, serialNo, name, description, numberInStock, price) VALUES (?,?,?,?,?,?)";

    private Item item;
    private List<Item> items = new ArrayList<>();

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock",
            "StockItemsUser", "Karel01+");

    Statement statement = connection.createStatement();


    public Stock() throws SQLException {
    }

    @Override
    public Item loadItemById(Integer id) throws SQLException {
        ResultSet resultset = statement.executeQuery("SELECT * FROM stockitems WHERE id =" + id);
        while (resultset.next()) {

            item = new Item();

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
        //deletion if numberInStock is set 0
        statement.executeUpdate(("DELETE FROM stockitems WHERE numberInStock = " + 0));

        //deletion if numberInStock is set null
        //statement.executeUpdate(("DELETE FROM stockitems WHERE numberInStock is " + null));
    }

    @Override
    public List<Item> loadAllAvailableItems() throws SQLException {
        ResultSet resultset = statement.executeQuery("SELECT * FROM stockitems");
        while (resultset.next()) {

            item = new Item();

            item.setId(resultset.getInt("id"));
            item.setPartNo(resultset.getString("partNo"));
            item.setSerialNo(resultset.getString("serialNo"));
            item.setName(resultset.getString("name"));
            item.setDescription(resultset.getString("description"));
            item.setNumberInStock(resultset.getInt("numberInStock"));
            item.setPrice(resultset.getBigDecimal("price"));

            items.add(item);
        }
        return items;
    }

    @Override
    public void saveItem(Item item) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM);
        preparedStatement.setString(1, item.getPartNo());
        preparedStatement.setString(2, item.getSerialNo());
        preparedStatement.setString(3, item.getName());
        preparedStatement.setString(4, item.getDescription());
        preparedStatement.setInt(5, item.getNumberInStock());
        preparedStatement.setBigDecimal(6, item.getPrice());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) throws SQLException {
        statement.executeUpdate(("Update stockitems SET price =" + newPrice + " WHERE id = " + id));
    }
}
