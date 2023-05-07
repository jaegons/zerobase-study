package com.example.zerobasestudy.web.controllers;

import com.example.zerobasestudy.web.bookmark.BookMark;
import com.example.zerobasestudy.web.bookmark.BookMarkRepository;
import com.example.zerobasestudy.web.jdbc.JdbcBookMarkRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/bookmark/manage")
public class BookMarkManageServlet extends HttpServlet {
    private BookMarkRepository bookMarkRepository;

    @Override
    public void init(){
        bookMarkRepository = new JdbcBookMarkRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookMark> bookMarkList = bookMarkRepository.findAll();
        req.setAttribute("bookMarkList", bookMarkList);
        req.getRequestDispatcher("/WEB-INF/bookmark.jsp").forward(req, resp);
    }

}
