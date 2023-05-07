package com.example.zerobasestudy.web.bookmark;

import java.util.List;

public interface BookMarkRepository {
    void createBookMark(BookMark bookMark);

    void updateBookMark(BookMark bookMark);

    BookMark findById(Long id);
    List<BookMark> findAll();
    void deleteBookMarkById(Long id);
}
