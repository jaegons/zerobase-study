package com.example.zerobasestudy.web.controllers;

import com.example.zerobasestudy.apiclient.WifiApiClient;
import com.example.zerobasestudy.web.bookmark.BookMark;
import com.example.zerobasestudy.web.bookmark.BookMarkRepository;
import com.example.zerobasestudy.web.jdbc.JdbcBookMarkRepository;
import com.example.zerobasestudy.web.jdbc.JdbcWifiRepository;
import com.example.zerobasestudy.web.wifi.WifiService;
import com.example.zerobasestudy.web.wifi.WifiDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/wifi/details/*")
public class WifiDetailsServlet extends HttpServlet {
    private WifiService wifiService;
    private BookMarkRepository bookMarkRepository;

    @Override
    public void init(){
        wifiService = new WifiService(new WifiApiClient(), new JdbcWifiRepository());
        bookMarkRepository = new JdbcBookMarkRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getPathInfo().substring(1);

        try {
            Long id = Long.valueOf(idStr);

            WifiDto wifi = wifiService.getWifiById(id);
            List<BookMark> bookMarkList = bookMarkRepository.findAll();

            req.setAttribute("wifi", wifi);
            req.setAttribute("bookMarkList", bookMarkList);

            req.getRequestDispatcher("/WEB-INF/detail.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid path");
        }
    }
}
