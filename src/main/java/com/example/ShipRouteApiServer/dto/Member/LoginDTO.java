package com.example.ShipRouteApiServer.dto.Member;

import com.example.ShipRouteApiServer.Entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

public class LoginDTO implements UserDetails {

    private final MemberEntity memberEntity;

    public LoginDTO(MemberEntity memberEntity){
        this.memberEntity = memberEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberEntity.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getUsername() {
        return memberEntity.getLoginId();
    }

    @Override
    public String getPassword() {
        return memberEntity.getPassword();
    }


}
