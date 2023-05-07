package com.example.zerobasestudy.web.jdbc.mapper;

import com.example.zerobasestudy.web.bookmark.BookMark;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookMarkMapper implements RowMapper<BookMark> {
    @Override
    public BookMark mapRow(ResultSet rs, int rowNum) throws SQLException {

        return BookMark.builder().id(rs.getLong("id"))
                .createdDateTime(LocalDateTime.parse(rs.getString("created_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .lastModified(rs.getString("last_modified") != null ? LocalDateTime.parse(rs.getString("last_modified"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null)
                .orderNum(rs.getInt("order_num"))
                .name(rs.getString("name"))
                .build();
    }
}
