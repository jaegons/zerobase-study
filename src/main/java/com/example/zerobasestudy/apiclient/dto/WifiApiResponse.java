package com.example.zerobasestudy.apiclient.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class WifiApiResponse {
    @SerializedName("TbPublicWifiInfo")
    TbPublicWifiInfo tbPublicWifiInfo;

}
