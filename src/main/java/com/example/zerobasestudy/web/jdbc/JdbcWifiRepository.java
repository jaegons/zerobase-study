package com.example.zerobasestudy.web.jdbc;

import com.example.zerobasestudy.web.jdbc.mapper.WifiRowMapper;
import com.example.zerobasestudy.web.wifi.Wifi;
import com.example.zerobasestudy.web.wifi.WifiRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class JdbcWifiRepository implements WifiRepository {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new DefaultDataSource());

    @Override
    public void saveWifiList(List<Wifi> wifiList) {

        String sql = "INSERT IGNORE INTO wifi (mgr_num, wifi_name, wrdofc, road_name_address, lot_number_address, installed_floor, installed_type, installed_mby, service_type, wifi_network_type, installed_year, inout_door, network_environment, latitude, longitude, work_date_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Wifi wifi = wifiList.get(i);
                ps.setString(1, wifi.getMgrNum());
                ps.setString(2, wifi.getWifiName());
                ps.setString(3, wifi.getWrdofc());
                ps.setString(4, wifi.getRoadNameAddress());
                ps.setString(5, wifi.getLotNumberAddress());
                ps.setString(6, wifi.getInstalledFloor());
                ps.setString(7, wifi.getInstalledType());
                ps.setString(8, wifi.getInstalledMby());
                ps.setString(9, wifi.getServiceType());
                ps.setString(10, wifi.getWifiNetworkType());
                ps.setString(11, wifi.getInstalledYear());
                ps.setString(12, wifi.getInoutDoor());
                ps.setString(13, wifi.getNetworkEnvironment());
                ps.setBigDecimal(14, wifi.getLatitude());
                ps.setBigDecimal(15, wifi.getLongitude());
                ps.setObject(16, wifi.getWorkDateTime());
            }

            @Override
            public int getBatchSize() {
                return wifiList.size();
            }
        });


    }

    @Override
    public List<Wifi> findWifiNearby(BigDecimal latitude, BigDecimal longitude, int limit) {
        final String sql = "SELECT * FROM wifi ORDER BY ((latitude - ?) * (latitude - ?) + (longitude - ?) * (longitude - ?)) ASC LIMIT ?";
        Object[] args = new Object[]{latitude, latitude, longitude, longitude, limit};
        return jdbcTemplate.query(sql, args, new WifiRowMapper());
    }

    @Override
    public Wifi findWifiById(Long id) {
        String sql = "SELECT * FROM wifi WHERE id = ?";
        Object[] params = {id};
        RowMapper<Wifi> mapper = new WifiRowMapper();

        Wifi wifi = jdbcTemplate.queryForObject(sql, params, mapper);
        return wifi;

    }

    @Override
    public long getCount() {
        String sql = "SELECT COUNT(*) FROM wifi";
        return jdbcTemplate.queryForObject(sql, Long.class);

    }


}
