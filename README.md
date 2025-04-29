# Vessel-location-information-coastal-AIS-API

## 소개
- 선박라이브서비스의 API 서버 입니다.
- 사용자의 로그인,회원가입,과거항적 조회 요청을 처리합니다.

## 요구사항
- JAVA 21.0.5 
- SringBoot 3.3.5

## 실행 가이드
```
git clone https://github.com/hhh88261/Vessel-location-information-coastal-AIS-API.git 
cd Vessel-location-information-coastal-AIS-API
```

## 명세서

로그인
- username, password를 포함한 필드를 JSON 형태로 넘겨줍니다.
- 로그인에 성공 시 JWT가 Refresh Token과 Access Token를 발급합니다.

Request Field 
|Path|Type|Description|
|-----|-----|-----|
|username|String|"admin"|
|password|String|"1234"|

Example request
```
GET http://localhost:8888/login
content type: json

{
  username : "admin3"
  password : "1234"
}
```

Example response

```
vary : Origin 
vary : Access-Control-Request-Method
vary : Access-Control-Request-Headers
access : eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6ImFjY2VzcyIsInVzZXJuYW1lIjoiYWRtaW4zIiwicm9sZSI6IlRlc3RSb2xlIiwiaWF0IjoxNzQ1OTMzOTE1LCJleHAiOjE3NDU5MzQ1MTV9.sqYdf2_r84qHCYu7aRjAjd6_iG2vpCIBjqDJ-Pdx5e4
Set-Cookie : refresh=eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6InJlZnJlc2giLCJ1c2VybmFtZSI6ImFkbWluMyIsInJvbGUiOiJUZXN0Um9sZSIsImlhdCI6MTc0NTkzMzkxNSwiZXhwIjoxNzQ2MDIwMzE1fQ.lFo1jneMNtp6EzgZKNL3ZdKY7AXup5_DcUcq29rPNmA; Max-Age=86400; Expires=Wed, 30 Apr 2025 13:38:35 GMT;
X-Content-Type-Options : nosniff
X-Content-Type-Options : 0
Cache-Control : no-cache, no-store, max-age=0, must-revalidate
Pragma : no-cache
Expires : 0
X-Frame-Options : DENY
Content-Length : 0
Date : Tue, 29 Apr 2025 13:38:35 GMT
Keep-Alive : timeout=60
Connection : keep-alive

```
- access에서 Access Token을 확인할 수 있습니다.

Exmaple cookie response

```
Name : Refresh
Value : eyJhdGco...
Domain : Localhost
Path : /
Expires : Web, 30 Apr 2025 13:00:19 GMT
HttpOnly : true
Secure : false
```

- Cookie에서 Refresh Token을 확인할 수 있습니다.

- - -

과거 항적 조회
- 날짜와 시간을 포함한 필드를 넘겨 결과를 요청합니다.

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

>>>>>>> 9b72395d8504257f7d3e08d5c216e87766cf7d66

```




