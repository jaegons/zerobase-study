package com.example.zerobasestudy.web.jdbc;

import com.example.zerobasestudy.web.jdbc.mapper.BookMarkMapper;
import com.example.zerobasestudy.web.bookmark.BookMarkRepository;
import com.example.zerobasestudy.web.bookmark.BookMark;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcBookMarkRepository implements BookMarkRepository {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new DefaultDataSource());

    @Override
    public void createBookMark(BookMark bookMark) {
        String sql = "INSERT INTO bookmark(order_num, name, created_date_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, bookMark.getOrderNum(), bookMark.getName(), bookMark.getCreatedDateTime());

    }

    @Override
    public void updateBookMark(BookMark bookMark) {

        String sql = "UPDATE bookmark SET order_num = ?, name = ?, last_modified = ? WHERE id = ?";
        jdbcTemplate.update(sql, bookMark.getOrderNum(), bookMark.getName(), bookMark.getLastModified(), bookMark.getId());

    }

    @Override
    public BookMark findById(Long id) {
        String sql = "SELECT * FROM bookmark WHERE id = ?";
        List<BookMark> result = jdbcTemplate.query(sql, new Object[]{id}, new BookMarkMapper());
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<BookMark> findAll() {
        String sql = "SELECT * FROM bookmark";
        return jdbcTemplate.query(sql, new BookMarkMapper());
    }
    @Override
    public void deleteBookMarkById(Long id) {
        String sql = "DELETE FROM bookmark WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
