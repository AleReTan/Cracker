package com.netcracker.demo.service;

import com.netcracker.demo.utility.CookieUtil;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class AuthService {

    public static final String ADDITION_URL = "/login";

    public static final String BASE = "Base ";
    public static final String SESSION = "Session";

    @Autowired
    private UncRestTemplate restTemplate;

    public String getRole(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
     try {
         ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL, HttpMethod.GET, String.class);
         List<String> header = response.getHeaders().getValuesAsList(HttpHeaders.AUTHORIZATION);
         if (!header.isEmpty()) {
             String token = header.get(0);
             if (token != null && token.startsWith(AuthService.SESSION)) {
                 CookieUtil.create(httpServletResponse, CookieUtil.COOKIE_NAME, token, -1, CookieUtil.LOCALHOST);
             }
         }
         return response.getBody();
     }catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
            return null;
        }
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

    public static  void sendRedirectIfError(HttpClientErrorException e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpStatus status = e.getStatusCode();
        if (status != HttpStatus.OK) {
            String errorPage = "/error";
                httpServletRequest.setAttribute("HttpStatus", status.getReasonPhrase());
                httpServletResponse.setStatus(status.value());
        }
    }

    public static  void sendRedirectIfError(HttpStatusCodeException e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpStatus status = e.getStatusCode() ;
        if (status != HttpStatus.OK) {
            String errorPage = "/error";
            httpServletRequest.setAttribute("HttpStatus", status.getReasonPhrase());
            httpServletResponse.setStatus(status.value());

        }
    }
}
