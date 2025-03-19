package com.example.ShipRouteApiServer.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
* 아이디와 패스워드를 검증하는 필터
* UsernamePasswordAuthenticationFilter 상속
*/

public class LoginFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    /*
    * Request와 Response를 인자로 받음
    * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{

        // 클라이언트 요청 메시지에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // Spring Security에서 username과 password 검증을 위해 Token에 담기
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password,null);

        return authenticationManager.authenticate(authenticationToken);
    }
}
