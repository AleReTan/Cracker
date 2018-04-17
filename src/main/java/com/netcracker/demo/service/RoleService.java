package com.netcracker.demo.service;


import com.netcracker.demo.utility.CookieUtil;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Service
public class RoleService {

    public static final String LOGIN_URL = "/role";

    @Autowired
    private UncRestTemplate restTemplate;

    public String getRole(HttpServletRequest req, HttpServletResponse res) {
        ResponseEntity<String> response = restTemplate.exchange(req, res, LOGIN_URL, HttpMethod.GET, String.class);
        if(response == null){
            return null;
        }
        response.getHeaders();
        List<String> header = response.getHeaders().getValuesAsList(HttpHeaders.AUTHORIZATION);
        if (!header.isEmpty()) {
            String token = header.get(0);
            if (token != null && token.startsWith(AuthService.SESSION)) {
                CookieUtil.create(res, CookieUtil.COOKIE_NAME, token, -1, CookieUtil.LOCALHOST);
            }
        }
        return response.getBody();
    }

}
