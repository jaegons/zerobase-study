package com.example.zerobasestudy.web.bookmark;

import com.example.zerobasestudy.web.jdbc.dto.BookMarkWifiDto;

import java.util.List;

public interface BookMarkWifiRepository {

    void createBookMarkWifi(BookMarkWifi bookMarkWifi);
    void deleteById(Long id);
    List<BookMarkWifiDto> findAll();
}
