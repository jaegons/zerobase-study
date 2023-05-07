package com.example.zerobasestudy.web.controllers.api;

import com.example.zerobasestudy.web.history.SearchHistoryRepository;
import com.example.zerobasestudy.web.jdbc.JdbcSearchHistoryRepository;
import com.example.zerobasestudy.web.history.SearchHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history/*")
public class SearchHistoryServlet extends HttpServlet {

    private SearchHistoryRepository searchHistoryRepository;

    @Override
    public void init(){
        searchHistoryRepository = new JdbcSearchHistoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SearchHistory> historyList = searchHistoryRepository.findAll();
        req.setAttribute("historyList", historyList);
        req.getRequestDispatcher("/WEB-INF/history.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        String idStr = req.getPathInfo().substring(1);


        try {
            Long id = Long.valueOf(idStr);
            searchHistoryRepository.deleteSearchHistoryById(id);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
