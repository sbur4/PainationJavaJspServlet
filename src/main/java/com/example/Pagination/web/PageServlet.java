package com.example.Pagination.web;

import com.example.Pagination.dao.impl.ProductDaoImpl;
import com.example.Pagination.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/page")
public class PageServlet extends HttpServlet {
    private static final int SHIFT = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDaoImpl productDao = new ProductDaoImpl();
        int page = 1;
        int pageSize = 5;
        double size = 0;
        try {
            if (request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            List<Product> products = productDao.findProductsForPagination(pageSize, (page - 1) * pageSize);
            size = productDao.getProductsSizeForPagination();
            int pageCount = (int) Math.ceil(size / pageSize);
            int minPagePossible = page - SHIFT < 1 ? 1 : page - SHIFT;
            int maxPagePossible = page + SHIFT > pageCount ? pageCount : page + SHIFT;
            request.setAttribute("products", products);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("page", page);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("minPossiblePage", minPagePossible);
            request.setAttribute("maxPossiblePage", maxPagePossible);
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        second variant
//        ProductDaoImpl productDao = new ProductDaoImpl();
//        String paramPage = req.getParameter("page");
//        String paramPageSize = req.getParameter("pageSize");
//        int page = Integer.parseInt(paramPage);
//        int pageSize = Integer.parseInt(paramPageSize);
////        int page = 1;
////        int pageSize = 5;
//        List<Product> products = null;
//        double size = 0;
//        try {
//            products = productDao.findProductsForPagination(pageSize, pageSize * (page - 1)); //
//            size = productDao.getProductsSizeForPagination();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        int minPagePossible = page - SHIFT < 1 ? 1 : page - SHIFT;
//        int pageCount = (int) Math.ceil(size / pageSize);
//        int maxPagePossible = page + SHIFT > pageCount ? pageCount : page + SHIFT;
//        req.setAttribute("products", products);
//        req.setAttribute("pageCount", pageCount);
//        req.setAttribute("page", page);
//        req.setAttribute("pageSize", pageSize);
//        req.setAttribute("minPossiblePage", minPagePossible);
//        req.setAttribute("maxPossiblePage", maxPagePossible);
//        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
