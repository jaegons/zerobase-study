package com.example.zerobasestudy.web.wifi;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
@Getter
public class Wifi {


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
    private LocalDateTime workDateTime;


}
