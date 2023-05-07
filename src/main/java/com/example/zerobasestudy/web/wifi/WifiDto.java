package com.example.zerobasestudy.web.wifi;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class WifiDto {

    private Long id;
    private String mgrNum;
    private String wifiName;
    private String wrdofc;
    private String roadNameAddress;
    private String lotNumberAddress;

    private String installedFloor;
    private String installedType;
    private String installedMby;
    private String serviceType;
    private String wifiNetworkType;
    private String installedYear;
    private String inoutDoor;
    private String networkEnvironment;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private double dist;
    private LocalDateTime workDateTime;

    public static WifiDto fromEntity(Wifi wifi) {
        return WifiDto.builder()
                .id(wifi.getId())
                .inoutDoor(wifi.getInoutDoor())
                .installedFloor(wifi.getInstalledFloor())
                .mgrNum(wifi.getMgrNum())
                .wrdofc(wifi.getWrdofc())
                .latitude(wifi.getLatitude())
                .longitude(wifi.getLongitude())
                .installedType(wifi.getInstalledType())
                .wifiNetworkType(wifi.getWifiNetworkType())
                .installedMby(wifi.getInstalledMby())
                .roadNameAddress(wifi.getRoadNameAddress())
                .lotNumberAddress(wifi.getLotNumberAddress())
                .networkEnvironment(wifi.getNetworkEnvironment())
                .workDateTime(wifi.getWorkDateTime())
                .wifiName(wifi.getWifiName())
                .serviceType(wifi.getServiceType())
                .installedYear(wifi.getInstalledYear())
                .build();
    }

    public static WifiDto fromEntityWithDist(Wifi wifi, double dist) {
        return WifiDto.builder()
                .dist(dist)
                .id(wifi.getId())
                .inoutDoor(wifi.getInoutDoor())
                .installedFloor(wifi.getInstalledFloor())
                .mgrNum(wifi.getMgrNum())
                .wrdofc(wifi.getWrdofc())
                .latitude(wifi.getLatitude())
                .longitude(wifi.getLongitude())
                .wifiNetworkType(wifi.getWifiNetworkType())
                .installedType(wifi.getInstalledType())
                .installedMby(wifi.getInstalledMby())
                .roadNameAddress(wifi.getRoadNameAddress())
                .lotNumberAddress(wifi.getLotNumberAddress())
                .networkEnvironment(wifi.getNetworkEnvironment())
                .workDateTime(wifi.getWorkDateTime())
                .wifiName(wifi.getWifiName())
                .serviceType(wifi.getServiceType())
                .installedYear(wifi.getInstalledYear())
                .build();
    }

}
