package com.example.Pagination.web;

import com.example.Pagination.dao.impl.ProductDaoImpl;
import com.example.Pagination.dao.interfaces.ProductDao;
import com.example.Pagination.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.Pagination.constant.ConstantNames.SORT_BY_NAME;

public class SortByName {
    ProductDao productDao = new ProductDaoImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute(request.getParameter(SORT_BY_NAME), sortedProductByName());
        response.sendRedirect("result.jsp");
    }

    public List<Product> sortedProductByName() throws Exception {
        List<Product> products = productDao.getAllProduct();
        products.sort(((n1, n2) -> {
            String name1 = n1.getName();
            String name2 = n2.getName();
            return name1.compareTo(name2);
        }));
        return products;
    }
}
