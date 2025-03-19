package com.example.ShipRouteApiServer.Service;

import com.example.ShipRouteApiServer.Entity.MemberEntity;
import com.example.ShipRouteApiServer.Repository.MemberRepository;
import com.example.ShipRouteApiServer.dto.Member.JoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MemberService {

    private MemberRepository memberRepository;

    // 생성자를 통해 MemberRepository 주입
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /* 회원가입 기능
     * 회원정보를 담고 있는 JoinDTO
     */
    public void joinProcess(JoinDTO joinDTO) {
        String loginid = joinDTO.getLoginId();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        // 로그인 ID로 회원이 존재하는지 확인
        Boolean idExist = memberRepository.existsByLoginId(loginid);

        if (idExist) {
            System.out.println("회원이 이미 존재함!");
            return;
        }

        // 새로운 회원 정보 생성
        MemberEntity memberData = new MemberEntity();

        // 각 데이터 삽입
        memberData.setLoginId(loginid);
        memberData.setPassword(password);
        memberData.setEmail(email);
        memberData.setRole("TestRole");

        // DB에 회원정보 저장
        memberRepository.save(memberData);
    }

}
