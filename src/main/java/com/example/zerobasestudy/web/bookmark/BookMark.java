package com.example.zerobasestudy.web.bookmark;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
@Builder
@Getter
@ToString
public class BookMark {

    private Long id;
    private int orderNum;
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModified;

}
