package com.example.zerobasestudy.web.jdbc.mapper;

import com.example.zerobasestudy.web.jdbc.dto.BookMarkWifiDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BookMarkWifiDtoMapper implements RowMapper<BookMarkWifiDto> {
    @Override
    public BookMarkWifiDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BookMarkWifiDto.builder()
                .id(rs.getLong("id"))
                .createdDateTime(rs.getObject("created_date_time", LocalDateTime.class))
                .bookMarkName(rs.getString("bookMarkName"))
                .wifiName(rs.getString("wifiName"))
                .build();
    }
}
