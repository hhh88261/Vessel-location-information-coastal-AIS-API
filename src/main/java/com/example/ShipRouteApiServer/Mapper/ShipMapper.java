package com.example.ShipRouteApiServer.Mapper;

import com.example.ShipRouteApiServer.dto.ShipDTO;
import com.example.ShipRouteApiServer.dto.ShipResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShipMapper {
    @Select("SELECT Mmsi, Lon, Lat, TRUE_HEAD, SHIP_TIME " +
            "FROM shiproute " +
            "WHERE SHIP_TIME BETWEEN #{startTime} AND #{endTime} AND TRUNC(DATE_RATE) = TO_DATE(#{shipDate}, 'YYYY-MM-DD')")
    List<ShipResultDTO> findShipRoute(String shipDate, String startTime, String endTime);
}