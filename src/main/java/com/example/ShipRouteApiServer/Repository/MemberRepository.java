package com.example.ShipRouteApiServer.Repository;


import com.example.ShipRouteApiServer.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 이메일로 사용자 조회
    Optional<MemberEntity> findByEmail(String email);
}
