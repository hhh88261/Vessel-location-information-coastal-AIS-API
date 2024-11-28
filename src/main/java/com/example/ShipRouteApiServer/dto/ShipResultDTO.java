package com.example.ShipRouteApiServer.dto;

import java.time.LocalTime;

public class ShipResultDTO {
    private String Mmsi;
    private double Lon;
    private double Lat;
    private String TRUE_HEAD;
    private String SHIP_TIME;

    // 기본 생성자
    public ShipResultDTO() {}

    public String getMmsi() {
        return Mmsi;
    }

    public void setMmsi(String Mmsi) {
        this.Mmsi = Mmsi;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double Lon) {
        this.Lon = Lon;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public String getTureHead() {
        return TRUE_HEAD;
    }

    public void setTureHead(String TRUE_HEAD) {
        this.TRUE_HEAD = TRUE_HEAD;
    }

    public String getShipTime() {
        return SHIP_TIME;
    }

    public void setShipTime(String SHIP_TIME) {
        this.SHIP_TIME = SHIP_TIME;
    }

    @Override
    public String toString() {
        return "ShipResultDTO" + Mmsi + "," + Lon + "," + Lat + "," + TRUE_HEAD + "," + SHIP_TIME;
    }
}
