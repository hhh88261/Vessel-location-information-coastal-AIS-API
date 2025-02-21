package com.example.ShipRouteApiServer.dto;

// DTO는 계층간 데이터 교환
public class ShipDTO {
    public String shipDate;
    public String startTime;
    public String endTime;
    public String trueHead;

    public String getShipDate() {
        return shipDate;
    }

    public void setDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
