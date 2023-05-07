package com.example.zerobasestudy.apiclient.dto;

import com.example.zerobasestudy.web.wifi.Wifi;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class WifiInfo {
    public String X_SWIFI_MGR_NO;

    public String X_SWIFI_WRDOFC;

    public String X_SWIFI_MAIN_NM;

    public String X_SWIFI_ADRES1;

    public String X_SWIFI_ADRES2;

    public String X_SWIFI_INSTL_FLOOR;

    public String X_SWIFI_INSTL_TY;

    public String X_SWIFI_INSTL_MBY;

    public String X_SWIFI_SVC_SE;

    public String X_SWIFI_CMCWR;

    public String X_SWIFI_CNSTC_YEAR;

    public String X_SWIFI_INOUT_DOOR;

    public String X_SWIFI_REMARS3;

    public BigDecimal LAT;

    public BigDecimal LNT;

    public LocalDateTime WORK_DTTM;

    public Wifi toEntity() {
        return Wifi.builder()
                .mgrNum(X_SWIFI_MGR_NO)
                .wrdofc(X_SWIFI_WRDOFC)
                .wifiName(X_SWIFI_MAIN_NM)
                .roadNameAddress(X_SWIFI_ADRES1)
                .lotNumberAddress(X_SWIFI_ADRES2)
                .installedFloor(X_SWIFI_INSTL_FLOOR)
                .installedType(X_SWIFI_INSTL_TY)
                .installedMby(X_SWIFI_INSTL_MBY)
                .wifiNetworkType(X_SWIFI_CMCWR)
                .serviceType(X_SWIFI_SVC_SE)
                .installedYear(X_SWIFI_CNSTC_YEAR)
                .inoutDoor(X_SWIFI_INOUT_DOOR)
                .networkEnvironment(X_SWIFI_REMARS3)
                .latitude(LAT)
                .longitude(LNT)
                .workDateTime(WORK_DTTM).build();
    }
}
