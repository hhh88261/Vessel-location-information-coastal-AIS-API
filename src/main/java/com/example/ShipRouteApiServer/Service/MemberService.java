package com.example.ShipRouteApiServer.Service;

import com.example.ShipRouteApiServer.Entity.MemberEntity;
import com.example.ShipRouteApiServer.Repository.MemberRepository;
import com.example.ShipRouteApiServer.dto.Member.JoinDTO;
import com.example.ShipRouteApiServer.dto.Member.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 생성자를 통해 MemberRepository 주입
    public MemberService(MemberRepository memberRepository,BCryptPasswordEncoder bCryptPasswordEncoder ) {

        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

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
        memberData.setPassword(bCryptPasswordEncoder.encode(password));
        memberData.setEmail(email);
        memberData.setRole("TestRole");

        // DB에 회원정보 저장
        memberRepository.save(memberData);
    }

    /* 로그인 기능
     *
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        MemberEntity loginData = memberRepository.findByLoginId(loginId);

        if (loginData == null) {
            throw new UsernameNotFoundException(loginId);
        }
        return new LoginDTO(loginData);
    }
}
