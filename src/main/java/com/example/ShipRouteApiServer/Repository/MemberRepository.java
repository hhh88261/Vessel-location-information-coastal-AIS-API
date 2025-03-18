package com.example.ShipRouteApiServer.Repository;


import com.example.ShipRouteApiServer.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    // 아이디와 패스워드로 사용자 조회


    // 회원가입시 동일한 아이디가 있는지 확인
    Boolean existsByLoginId(String loginid);
}
