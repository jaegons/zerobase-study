package com.example.zerobasestudy.web.wifi;

import java.math.BigDecimal;
import java.util.List;

public interface WifiRepository {
    void saveWifiList(List<Wifi> wifiList);
    List<Wifi> findWifiNearby(BigDecimal latitude, BigDecimal longitude, int limit);
    Wifi findWifiById(Long id);
    long getCount();
}
