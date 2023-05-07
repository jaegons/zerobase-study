package com.example.zerobasestudy.web.bookmark;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookMarkWifi {
    public BookMarkWifi(Long bookMarkId, Long wifiId, LocalDateTime createdDateTime) {
        this.bookMarkId = bookMarkId;
        WifiId = wifiId;
        this.createdDateTime = createdDateTime;
    }

    private Long id;
    private Long bookMarkId;
    private Long WifiId;
    private LocalDateTime createdDateTime;

}
