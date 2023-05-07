package com.example.zerobasestudy.web.jdbc.mapper;

import com.example.zerobasestudy.web.wifi.Wifi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WifiRowMapper implements RowMapper<Wifi> {
    @Override
    public Wifi mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Wifi.builder().id(rs.getLong("id"))
                .mgrNum(rs.getString("mgr_num"))
                .wifiName(rs.getString("wifi_name"))
                .wrdofc(rs.getString("wrdofc"))
                .roadNameAddress(rs.getString("road_name_address"))
                .lotNumberAddress(rs.getString("lot_number_address"))
                .installedFloor(rs.getString("installed_floor"))
                .installedType(rs.getString("installed_type"))
                .installedMby(rs.getString("installed_mby"))
                .serviceType(rs.getString("service_type"))
                .wifiNetworkType(rs.getString("wifi_network_type"))
                .installedYear(rs.getString("installed_year"))
                .inoutDoor(rs.getString("inout_door"))
                .networkEnvironment(rs.getString("network_environment"))
                .latitude(rs.getBigDecimal("latitude"))
                .longitude(rs.getBigDecimal("longitude"))
                .workDateTime(LocalDateTime.parse(rs.getString("work_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).build();
    }
}