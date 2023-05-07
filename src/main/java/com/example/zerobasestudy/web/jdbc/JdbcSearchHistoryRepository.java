package com.example.zerobasestudy.web.jdbc;

import com.example.zerobasestudy.web.history.SearchHistory;
import com.example.zerobasestudy.web.history.SearchHistoryRepository;
import com.example.zerobasestudy.web.jdbc.mapper.SearchHistoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcSearchHistoryRepository implements SearchHistoryRepository {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new DefaultDataSource());

    @Override
    public void createSearchHistory(SearchHistory searchHistory) {
        String sql = "INSERT INTO search_history (longitude, latitude, searched_date_time) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, searchHistory.getLongitude(), searchHistory.getLatitude(), searchHistory.getSearchedDateTime());
    }
    @Override
    public void deleteSearchHistoryById(Long id) {
        String sql = "DELETE FROM search_history WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<SearchHistory> findAll() {
        String sql = "SELECT * FROM search_history";
        return jdbcTemplate.query(sql, new SearchHistoryMapper());
    }
}
