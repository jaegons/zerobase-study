package com.example.zerobasestudy.apiclient.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class TbPublicWifiInfo {
    public int list_total_count;
    public ApiResult RESULT;
    public List<WifiInfo> row;
}
