package com.example.ShipRouteApiServer.parseJson;
import org.json.JSONArray;
import org.json.JSONObject;

public class parseMessage {

    public static JSONObject parseResult(String result) {
        // 주어진 데이터에서 ShipResultDTO 구분자로 각 데이터 항목을 나눕니다.
        String[] dataParts = result.split("ShipResultDTO");

        // JSON 배열 생성
        JSONArray jsonArray = new JSONArray();

        // 각 데이터 항목을 처리
        for (String part : dataParts) {
            if (part.trim().isEmpty()) continue;

            // 쉼표를 기준으로 각 항목을 분리
            String[] values = part.split(",");

            // ID, latitude, longitude, speed, timestamp 추출
            long id = Long.parseLong(values[0].trim());
            double latitude = Double.parseDouble(values[1].trim());
            double longitude = Double.parseDouble(values[2].trim());
            int speed = Integer.parseInt(values[3].trim());
            String timestamp = values[4].trim();

            // JSON 객체에 각 값 저장
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            jsonObject.put("speed", speed);
            jsonObject.put("timestamp", timestamp);

            // JSON 배열에 추가
            jsonArray.put(jsonObject);
        }

        // JSON 배열을 포함하는 최종 JSON 객체 반환
        JSONObject finalResult = new JSONObject();
        finalResult.put("data", jsonArray);

        return finalResult;
    }

}