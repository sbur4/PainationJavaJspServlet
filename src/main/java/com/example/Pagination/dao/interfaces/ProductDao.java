package com.example.Pagination.dao.interfaces;

import com.example.Pagination.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findProductsForPagination(int limit, int offset) throws Exception;

    int getProductsSizeForPagination() throws Exception;

    List<Product> getAllProduct() throws Exception;
}
