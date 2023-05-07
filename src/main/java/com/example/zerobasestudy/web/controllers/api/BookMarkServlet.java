package com.example.zerobasestudy.web.controllers.api;

import com.example.zerobasestudy.web.bookmark.BookMark;
import com.example.zerobasestudy.web.bookmark.BookMarkRepository;
import com.example.zerobasestudy.web.jdbc.JdbcBookMarkRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/bookmark/*")
public class BookMarkServlet extends HttpServlet {
    private BookMarkRepository bookMarkRepository;

    @Override
    public void init() {
        bookMarkRepository = new JdbcBookMarkRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nameStr = req.getParameter("name");
        String orderNumStr = req.getParameter("orderNum");
        if (nameStr != null && orderNumStr != null && !nameStr.isEmpty() && !orderNumStr.isEmpty()) {
            BookMark bookMark = BookMark.builder()
                    .name(nameStr)
                    .orderNum(Integer.parseInt(orderNumStr))
                    .createdDateTime(LocalDateTime.now()).build();
            bookMarkRepository.createBookMark(bookMark);
        }

        resp.sendRedirect("/bookmark/manage");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getPathInfo().substring(1);
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String body = sb.toString();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(body, JsonObject.class);
        String nameStr = json.get("name").getAsString();
        String orderNumStr = json.get("orderNum").getAsString();


        try {
            BookMark bookMark = bookMarkRepository.findById(Long.parseLong(idStr));
            if (bookMark != null) {
                BookMark updatedBookmark = BookMark.builder()
                        .id(bookMark.getId())
                        .orderNum(Integer.parseInt(orderNumStr))
                        .name(nameStr)
                        .createdDateTime(bookMark.getCreatedDateTime())
                        .lastModified(LocalDateTime.now())
                        .build();
                bookMarkRepository.updateBookMark(updatedBookmark);
            }

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String idStr = req.getPathInfo().substring(1);

        try {
            Long id = Long.parseLong(idStr);
            bookMarkRepository.deleteBookMarkById(id);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
