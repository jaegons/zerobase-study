package com.example.zerobasestudy.web.jdbc.mapper;

import com.example.zerobasestudy.web.history.SearchHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchHistoryMapper implements RowMapper<SearchHistory> {

    @Override
    public SearchHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return SearchHistory.builder()
                .id(rs.getLong("id"))
                .latitude(rs.getBigDecimal("latitude"))
                .searchedDateTime(LocalDateTime.parse(rs.getString("searched_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .longitude(rs.getBigDecimal("longitude"))
                .build();
    }
}
