package com.example.zerobasestudy.web.jdbc.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BookMarkWifiDto {
    private Long id;
    private LocalDateTime createdDateTime;
    private String bookMarkName;
    private String wifiName;

}
