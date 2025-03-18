package com.example.ShipRouteApiServer.dto.Member;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {
    private String loginId;
    private String password;
    private String email;

}
