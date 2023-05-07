package com.example.zerobasestudy.web.jdbc;

import com.example.zerobasestudy.web.bookmark.BookMarkWifi;
import com.example.zerobasestudy.web.bookmark.BookMarkWifiRepository;
import com.example.zerobasestudy.web.jdbc.dto.BookMarkWifiDto;
import com.example.zerobasestudy.web.jdbc.mapper.BookMarkWifiDtoMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcBookMarkWifiRepository implements BookMarkWifiRepository {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new DefaultDataSource());


    @Override
    public void createBookMarkWifi(BookMarkWifi bookMarkWifi) {
        String sql = "INSERT INTO bookmark_wifi(bookmark_id, wifi_id, created_date_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, bookMarkWifi.getBookMarkId(), bookMarkWifi.getWifiId(), bookMarkWifi.getCreatedDateTime());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM bookmark_wifi WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<BookMarkWifiDto> findAll() {
        String sql = "SELECT bw.id, bw.created_date_time, b.name as bookMarkName, w.wifi_name as wifiName " +
                "FROM bookmark_wifi bw " +
                "JOIN bookmark b ON bw.bookmark_id = b.id " +
                "JOIN wifi w ON bw.wifi_id = w.id";

        return jdbcTemplate.query(sql, new BookMarkWifiDtoMapper());
    }
}
