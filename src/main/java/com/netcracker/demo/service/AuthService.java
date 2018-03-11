package com.netcracker.demo.service;

import com.netcracker.demo.utility.UncRestTemplate;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AuthService {

    public static final String ADDITION_URL = "/login";
    public static final String COOKIE_NAME = "AUTH";
    public static final String BASE = "Base ";
    public static final String SESSION = "Session ";

    @Autowired
    private UncRestTemplate restTemplate;

    public String getRole(HttpServletResponse httpServletResponse){
        ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL, HttpMethod.GET, String.class);
        List<String> header = response.getHeaders().getValuesAsList(HttpHeaders.AUTHORIZATION);
        if(!header.isEmpty()) {
         String token =   header.get(0);
            if (token != null && token.startsWith(AuthService.SESSION)) {
                //httpServletResponse.addHeader(HttpHeaders.AUTHORIZATION,token);
                setCookie(httpServletResponse, token);
            }
        }
        return response.getBody();
    }

    public  String getToken(String login,String password){
        String originalInput = login +":"+ password;
        String token = "Base " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        return token;
    }

    public  String getToken(String sessionId){
        String token = "Session " + Base64.getEncoder().encodeToString(sessionId.getBytes());
        return token;
    }

    public void setCookie(HttpServletResponse response, String cookieValue){
        Cookie cookie = new Cookie("COOKIE_NAME",cookieValue);
        cookie.setDomain("localhost:8080");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public Cookie getCookie(HttpServletRequest request) {
        return WebUtils.getCookie(request, COOKIE_NAME);
    }
}
