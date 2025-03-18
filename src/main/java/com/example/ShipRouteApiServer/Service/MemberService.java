package com.example.ShipRouteApiServer.Service;

import com.example.ShipRouteApiServer.Entity.MemberEntity;
import com.example.ShipRouteApiServer.Repository.MemberRepository;
import com.example.ShipRouteApiServer.dto.Member.JoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /* 회원가입 기능
     *
     */
    public void joinProcess(JoinDTO joinDTO) {
        String loginid = joinDTO.getLoginId();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        Boolean idExist = memberRepository.existsByLoginId(loginid);

        if (idExist) {

            //System.out.println("회원이 이미 존재함!");
            return;
        }

        MemberEntity memberData = new MemberEntity();

        memberData.setLoginId(loginid);
        memberData.setPassword(password);
        memberData.setEmail(email);
        memberData.setRole("TestRole");

        memberRepository.save(memberData);
    }

}
