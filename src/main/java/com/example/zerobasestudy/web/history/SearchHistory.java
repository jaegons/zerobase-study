package com.example.zerobasestudy.web.history;


import lombok.Builder;
import lombok.Getter;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class SearchHistory {
    private Long id;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private LocalDateTime searchedDateTime;


}
