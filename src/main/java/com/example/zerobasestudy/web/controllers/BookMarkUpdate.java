package com.example.zerobasestudy.web.controllers;

import com.example.zerobasestudy.web.bookmark.BookMark;
import com.example.zerobasestudy.web.bookmark.BookMarkRepository;
import com.example.zerobasestudy.web.jdbc.JdbcBookMarkRepository;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/update-bookmark/*")
public class BookMarkUpdate extends HttpServlet {
    BookMarkRepository bookMarkRepository;

    @Override
    public void init(){
        bookMarkRepository = new JdbcBookMarkRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String idStr = req.getPathInfo().substring(1);
        try{
            BookMark bookMark = bookMarkRepository.findById(Long.parseLong(idStr));
            if (bookMark!= null){
                req.setAttribute("bookMark", bookMark);
                req.getRequestDispatcher("/WEB-INF/update-bookmark.jsp").forward(req, resp);
            }
        }catch (Exception e){
        }
    }
}
