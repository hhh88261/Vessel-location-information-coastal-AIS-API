package com.example.ShipRouteApiServer.Controller;

import com.example.ShipRouteApiServer.Service.ShipService;
import com.example.ShipRouteApiServer.dto.ShipDTO;
import com.example.ShipRouteApiServer.dto.ShipResultDTO;
import com.example.ShipRouteApiServer.parseJson.parseMessage;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// 과거 항로 조회 API
@RestController
@RequestMapping("/api")
public class ShipController {
    parseMessage parseMessage = new parseMessage();

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/ShipRoute")
    public SseEmitter getShipRoute(
            @RequestParam String shipDate,
            @RequestParam int startTime,
            @RequestParam int endTime
    ) {
        SseEmitter emitter = new SseEmitter(60 * 1000L);
        LocalTime start = LocalTime.of(startTime,0,0);
        LocalTime end = LocalTime.of(endTime,0,0);

        //클라이언트와 단방향 통신
        new Thread(() -> {
            try {
                LocalTime current = start;
                LocalTime EndTime = end;
                // 정수값을 HH:mm:ss 형태로 변환
                String StartTimeStamp = current.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String EndTimeStamp = EndTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                // 쿼리 결과 추출
                List<ShipResultDTO> data = shipService.findShipRoute(shipDate, StartTimeStamp, EndTimeStamp);
                System.out.println(data.size());

                // 데이터가 없을 경우 에러 메시지 전송
                if (data.isEmpty()) {
                    JSONObject errorMessage = new JSONObject();
                    errorMessage.put("error", "조회된 데이터가 없습니다.");
                    emitter.send(errorMessage.toString());
                    emitter.complete(); // Emitter 완료
                    return; // 더 이상의 처리를 중단
                }
                // SHIP_TIME을 초 단위로 조회
                while (!current.isAfter(EndTime)) {

                    // 현재 시간을 HH:mm:ss 형식으로 변환
                    String currentTimeStamp = current.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                    // 스트림을 사용하여 해당 시간에 맞는 레코드를 필터링하고, 같은 시간대는 그룹화
                    Map<String, List<ShipResultDTO>> groupedByTime = data.stream()
                            .filter(record -> record.getShipTime().equals(currentTimeStamp)) // timeStamp 비교
                            .collect(Collectors.groupingBy(ShipResultDTO::getShipTime));

                    // 그룹화된 데이터가 있을 경우 출력
                    groupedByTime.forEach((timeStamp, records) -> {
                        // timeStamp와 관련된 레코드들을 한 줄로 출력
                        String result = records.stream()
                                .map(record -> record.toString()) // 각 레코드를 문자열로 변환
                                .collect(Collectors.joining(", ")); // 한 줄로 연결
                        try {
                            // JSON 결과를 받아오기
                            JSONObject jsonData = parseMessage.parseResult(result);

                            // jsonData가 null인지 확인
                            if (jsonData != null) {
                                System.out.println(timeStamp + ": " + result);
                                System.out.println(jsonData.toString(4));
                                emitter.send(jsonData.toString());
                            } else {
                                System.out.println("Parsed JSON is null");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();  // 예외 메시지 출력
                        }
                    });
                    current = current.plusSeconds(1);
                } emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
                System.out.println("예외");
            }
        }).start();
        return emitter;
    }
}