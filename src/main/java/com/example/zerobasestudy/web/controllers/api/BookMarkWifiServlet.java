package com.example.zerobasestudy.web.controllers.api;

import com.example.zerobasestudy.web.bookmark.BookMarkWifi;
import com.example.zerobasestudy.web.bookmark.BookMarkWifiRepository;
import com.example.zerobasestudy.web.jdbc.JdbcBookMarkWifiRepository;
import com.example.zerobasestudy.web.jdbc.dto.BookMarkWifiDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/bookmark-wifi/*")
public class BookMarkWifiServlet extends HttpServlet {
    private BookMarkWifiRepository bookMarkWifiRepository;

    @Override
    public void init() {
        bookMarkWifiRepository = new JdbcBookMarkWifiRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookMarkWifiDto> bookMarkWifiDtoList = bookMarkWifiRepository.findAll();
        req.setAttribute("list", bookMarkWifiDtoList);
        req.getRequestDispatcher("/WEB-INF/bookmark-wifi.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String bookMarkIdStr = req.getParameter("bookMarkId");
        String wifiIdStr = req.getParameter("wifiId");
        try {
            Long bookMarkId = Long.valueOf(bookMarkIdStr);
            Long wifiId = Long.valueOf(wifiIdStr);

            bookMarkWifiRepository.createBookMarkWifi(new BookMarkWifi(bookMarkId, wifiId, LocalDateTime.now()));

        } catch (NumberFormatException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getPathInfo().substring(1);
        bookMarkWifiRepository.deleteById(Long.parseLong(id));
    }
}
