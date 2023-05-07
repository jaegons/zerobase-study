package com.example.zerobasestudy.web.wifi;

import com.example.zerobasestudy.apiclient.WifiApiClient;
import com.example.zerobasestudy.apiclient.dto.WifiInfo;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class WifiService {

    private static final int EARTH_RADIUS = 6371;
    private final WifiApiClient wifiApiClient;
    private final WifiRepository wifiRepository;

    public long loadData() {

        int totalCount = wifiApiClient.getTotalCount();
        int limitSize = 1000;
        int pageCount = (int) Math.ceil((double) totalCount / limitSize);
        IntStream.range(0, pageCount)
                .parallel()
                .forEach(page -> {
                    int start = 1 + page * limitSize;
                    int end = (page + 1) * limitSize;
                    List<Wifi> wifiList = wifiApiClient.requestData(start, end)
                            .stream()
                            .map(WifiInfo::toEntity)
                            .collect(Collectors.toList());
                    wifiRepository.saveWifiList(wifiList);

                });
        return wifiRepository.getCount();

    }

    public List<WifiDto> getWifiNearby(BigDecimal latitude, BigDecimal longitude, int limit) {
        return wifiRepository.findWifiNearby(latitude, longitude, limit).stream()
                .map(wifi -> WifiDto.fromEntityWithDist(wifi, calculateDistance(latitude, longitude, wifi.getLatitude(), wifi.getLongitude())))
                .collect(Collectors.toList());
    }

    public WifiDto getWifiById(Long id) {
        Wifi wifi = wifiRepository.findWifiById(id);
        return WifiDto.fromEntity(wifi);
    }

    private static double calculateDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        double lat1Rad = Math.toRadians(lat1.doubleValue());
        double lon1Rad = Math.toRadians(lon1.doubleValue());
        double lat2Rad = Math.toRadians(lat2.doubleValue());
        double lon2Rad = Math.toRadians(lon2.doubleValue());

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }


}
