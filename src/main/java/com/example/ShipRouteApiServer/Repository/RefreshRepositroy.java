package com.example.ShipRouteApiServer.Repository;

import com.example.ShipRouteApiServer.Entity.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepositroy extends JpaRepository<RefreshEntity, Long> {

    // refresh 토큰 존재 확인
    Boolean existsByRefresh(String refresh);

    // refresh 토큰 삭제
    @Transactional
    void deleteByRefresh(String refresh);

}
