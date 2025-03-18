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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loginId;
    private String password;
    private String email;
    private String role;

}
