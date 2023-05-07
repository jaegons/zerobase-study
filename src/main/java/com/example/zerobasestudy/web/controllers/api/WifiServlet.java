package com.example.zerobasestudy.web.controllers.api;

import com.example.zerobasestudy.apiclient.WifiApiClient;
import com.example.zerobasestudy.web.history.SearchHistory;
import com.example.zerobasestudy.web.history.SearchHistoryRepository;
import com.example.zerobasestudy.web.jdbc.JdbcSearchHistoryRepository;
import com.example.zerobasestudy.web.jdbc.JdbcWifiRepository;
import com.example.zerobasestudy.gsonutils.LocalDateTimeSerializer;
import com.example.zerobasestudy.web.wifi.WifiService;
import com.example.zerobasestudy.web.wifi.WifiDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/wifi")
public class WifiServlet extends HttpServlet {
    private WifiService wifiService;
    private SearchHistoryRepository searchHistoryRepository;

    @Override
    public void init() {
        wifiService = new WifiService(new WifiApiClient(), new JdbcWifiRepository());
        searchHistoryRepository = new JdbcSearchHistoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String latStr = req.getParameter("lat");
        String lntStr = req.getParameter("lnt");

        BigDecimal lat, lnt;
        try {
            lat = new BigDecimal(latStr);
            lnt = new BigDecimal(lntStr);

            searchHistoryRepository.createSearchHistory(SearchHistory.builder()
                    .longitude(lnt)
                    .latitude(lat)
                    .searchedDateTime(LocalDateTime.now()).build());

            List<WifiDto> wifiNearby = wifiService.getWifiNearby(lat, lnt, 20);

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer()).setPrettyPrinting().create();
            String json = gson.toJson(wifiNearby);

            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(json);
            out.flush();

        } catch (NumberFormatException | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid latitude or longitude");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        long count = wifiService.loadData();

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        try {
            PrintWriter writer = resp.getWriter();
            writer.print(count);
            writer.flush();

        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
