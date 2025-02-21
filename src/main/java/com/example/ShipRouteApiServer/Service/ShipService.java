package com.example.ShipRouteApiServer.Service;

import com.example.ShipRouteApiServer.Mapper.ShipMapper;
import com.example.ShipRouteApiServer.dto.ShipResultDTO;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipMapper shipMapper;

    public ShipService(ShipMapper shipMapper) {
        this.shipMapper = shipMapper;
    }

    public List<ShipResultDTO> findShipRoute(String shipDate, String StartTimeStamp, String EndTimeStamp) {
        System.out.println(shipDate + " " + StartTimeStamp + " " + EndTimeStamp);
        return shipMapper.findShipRoute(shipDate, StartTimeStamp, EndTimeStamp);
    }
}