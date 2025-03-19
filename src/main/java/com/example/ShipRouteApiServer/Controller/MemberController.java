package com.example.ShipRouteApiServer.Controller;

import com.example.ShipRouteApiServer.Service.MemberService;
import com.example.ShipRouteApiServer.dto.Member.JoinDTO;
import org.springframework.web.bind.annotation.*;

// 회원가입 API
@RestController
@RequestMapping
public class MemberController {
    private MemberService memberService;


    //Service 생성자
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    /*
     * 회원가입
     * 클라이언트에서 빋은 JoinDTO를 Service 호출 후 전달
     */
    @PostMapping("/join")
    public String testJoin(JoinDTO joinDTO) {
        memberService.joinProcess(joinDTO);
        return "success";
    }

}
