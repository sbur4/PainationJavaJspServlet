package com.example.Pagination.dao.impl;

import com.example.Pagination.connection.ConnectionPool;
import com.example.Pagination.dao.interfaces.ProductDao;
import com.example.Pagination.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.Pagination.constant.ConstantNames.*;

public class ProductDaoImpl implements ProductDao {
    private static final String GET_PRODUCTS = "SELECT * FROM product limit ? offset ?";
    private static final String GET_PRODUCTS_SIZE = "SELECT COUNT(*) FROM product";
    private static final String GET_PRODUCTS_FOR_SORT = "SELECT * FROM product";
    private static final String SORT_BY_NAME = "SELECT * FROM product WHERE";
    private static final String SORT_BY_PRICE = "SELECT * FROM product WHERE";

    public List<Product> findProductsForPagination(int limit, int offset) throws Exception {
        List<Product> products = new ArrayList<>();
        try (Connection con = ConnectionPool.getConnection();) {
            PreparedStatement pstmt = con.prepareStatement(GET_PRODUCTS);
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            ResultSet rs = pstmt.executeQuery(); //
            while (rs.next()) {
                products.add(extractProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static Product extractProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt(ID));
        product.setName(rs.getString(NAME));
        product.setDescription(rs.getString(DESCRIPTION));
        product.setPrice(rs.getDouble(PRICE));
        product.setImage_url(rs.getString(IMAGE_URL));
        return product;
    }

    public int getProductsSizeForPagination() throws Exception {
        int size = 0;
        try {
            Connection con = ConnectionPool.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(GET_PRODUCTS_SIZE);
            if (rs.next()) {
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Product> getAllProduct() throws Exception {
        List<Product> products = new ArrayList<>();
        try (Connection con = ConnectionPool.getConnection();) {
            PreparedStatement pstmt = con.prepareStatement(GET_PRODUCTS_FOR_SORT);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(extractProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}