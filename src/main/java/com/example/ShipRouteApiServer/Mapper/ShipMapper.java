package com.example.ShipRouteApiServer.Mapper;

import com.example.ShipRouteApiServer.dto.ShipDTO;
import com.example.ShipRouteApiServer.dto.ShipResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShipMapper {

    // SHIPROUTE 한 테이블만 조회
    @Select("SELECT Mmsi, Lon, Lat" +
            "FROM ship_locate " +
            "WHERE ship_date = #{shipDate} BETWEEN #{startTime} AND #{endTime}")


    // 날짜별로 분할된 SHIPROUTE 테이블 조회

//    @Select("SELECT Mmsi, Lon, Lat, TRUE_HEAD, SHIP_TIME " +
//            "FROM shiproute_${shipDate} " +
//            "WHERE SHIP_TIME BETWEEN #{startTime} AND #{endTime}")


    List<ShipResultDTO> findShipRoute(String shipDate, String startTime, String endTime);

}