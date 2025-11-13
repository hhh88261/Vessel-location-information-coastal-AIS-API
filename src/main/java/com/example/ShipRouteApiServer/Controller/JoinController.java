package com.example.ShipRouteApiServer.Controller;

import com.example.ShipRouteApiServer.Service.JoinService;
import com.example.ShipRouteApiServer.dto.Member.JoinDTO;
import org.springframework.web.bind.annotation.*;

// 회원가입 API
@RestController
@ResponseBody
public class JoinController {
    private final JoinService joinService;


    //Service 생성자
    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }


    /*
     * 회원가입
     * 클라이언트에서 빋은 JoinDTO를 Service 호출 후 전달
     */
    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return "회원가입 ok";
    }
}
