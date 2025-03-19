package com.example.ShipRouteApiServer.Entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


// 신규회원 엔티티
// 엔티티는 데이터베이스와 자바 객체를 매핑해주는 역할

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
    @SequenceGenerator(name = "customers_seq", sequenceName = "customers_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "loginid") // 컬럼명 명확히 지정
    private String loginId;

    private String password;

    private String email;

    private String role;

}
