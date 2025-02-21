# Vessel-location-information-coastal-AIS-API

## 소개
과거 선박 경로 정보를 제공하는 API 서비스

## 기술스택
- JAVA 21.0.5 
- SringBoot 3.3.5

## API 명세서

날짜
- 날짜와 시간은 과거 선박의 경로를 조회할 때 사용됩니다.

요청
- `GET` 요청을 통해 DB를 조회합니다.

Request Field 
|Path|Type|Description|
|-----|-----|-----|
|shipDate|String|2025-02-19|
|startTime|Int|21|
|endTime|Int|23|


Example request
```
GET http://localhost:8888/api/ShipRoute?shipDate=2025-02-19&startTime=21&endTime=23
content type: json

{
  "shipDate": 1,
  "startTime": "21"
  "endTime": "23"
}
```

Example response

```
vary : Origin 
vary : Access-Control-Request-Method
vary : Access-Control-Request-Headers
Content-Type : text/event-stream
Transfer-Encoding : chunked
Date : Fri, 21 Feb 2025 13:16:00 GMT
Keep-Alive : timeout=60
Connection : keep-alive

{
    "data": [
        {
            "latitude": 0.4339983333333333,
            "id": 123456789,
            "speed": 90,
            "longitude": 7.0928933333333335,
            "timestamp": "22:47:27"
        }
    ]
} 
```

에러
- 조회된 정보가 없을 때 아래와 같은 메시지를 출력합니다.

Request Field 
|Path|Type|Description|
|-----|-----|-----|
|shipDate|String|2002-02-19|
|startTime|Int|21|
|endTime|Int|23|

```
vary : Origin 
vary : Access-Control-Request-Method
vary : Access-Control-Request-Headers
Content-Type : text/event-stream
Transfer-Encoding : chunked
Date : Fri, 21 Feb 2025 13:16:00 GMT
Keep-Alive : timeout=60
Connection : keep-alive

{
    "error": "조회된 데이터가 없습니다."
}

