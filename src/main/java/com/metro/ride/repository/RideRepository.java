package com.metro.ride.repository;

import com.metro.ride.model.Ride;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RideRepository {
    private final JdbcTemplate jdbc;

    public RideRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Ride> findAll() {
        return jdbc.query(
            "select ride_id, customer_id, driver_id, pickup_area, drop_area, ride_status, distance_km, fare, booked_at from rides order by ride_id",
            (rs, row) -> new Ride(
                rs.getInt("ride_id"),
                rs.getInt("customer_id"),
                rs.getInt("driver_id"),
                rs.getString("pickup_area"),
                rs.getString("drop_area"),
                rs.getString("ride_status"),
                rs.getBigDecimal("distance_km"),
                rs.getBigDecimal("fare"),
                rs.getTimestamp("booked_at").toLocalDateTime()
            )
        );
    }

    public Ride findById(int id) {
        return jdbc.queryForObject(
            "select ride_id, customer_id, driver_id, pickup_area, drop_area, ride_status, distance_km, fare, booked_at from rides where ride_id = ?",
            (rs, row) -> new Ride(
                rs.getInt("ride_id"),
                rs.getInt("customer_id"),
                rs.getInt("driver_id"),
                rs.getString("pickup_area"),
                rs.getString("drop_area"),
                rs.getString("ride_status"),
                rs.getBigDecimal("distance_km"),
                rs.getBigDecimal("fare"),
                rs.getTimestamp("booked_at").toLocalDateTime()
            ),
            id
        );
    }

    public int updateStatus(int id, String status) {
        return jdbc.update("update rides set ride_status = ? where ride_id = ?", status, id);
    }

    public int delete(int id) {
        return jdbc.update("delete from rides where ride_id = ?", id);
    }

    public List<Object> driverRevenue() {
        return jdbc.query(
            "select d.driver_id, d.full_name, count(r.ride_id) rides, coalesce(sum(r.fare),0) revenue " +
            "from drivers d left join rides r on d.driver_id = r.driver_id and r.ride_status = 'COMPLETED' " +
            "group by d.driver_id, d.full_name order by revenue desc limit 10",
            (rs, row) -> java.util.Map.of(
                "driverId", rs.getInt("driver_id"),
                "driverName", rs.getString("full_name"),
                "rides", rs.getLong("rides"),
                "revenue", rs.getBigDecimal("revenue")
            )
        );
    }
}
