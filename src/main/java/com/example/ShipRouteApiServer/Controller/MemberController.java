package com.example.ShipRouteApiServer.Controller;

import com.example.ShipRouteApiServer.Service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 로그인 API
@RestController
@RequestMapping("/api")
public class MemberController {
    private MemberService memberService;
}
