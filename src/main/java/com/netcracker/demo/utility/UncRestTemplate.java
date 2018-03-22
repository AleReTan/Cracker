package com.netcracker.demo.utility;


import com.netcracker.demo.models.AuthThreadLocalTO;
import com.netcracker.demo.service.AuthService;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class UncRestTemplate {

    public static final String BASE_URL = "http://localhost:8082";

    private RestTemplate restTemplate;

    public UncRestTemplate() {
        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
    }

    public <T> ResponseEntity<T> exchange(HttpServletRequest req, HttpServletResponse res, String additionUrl,
                                          HttpMethod method,
                                          Class<T> responseType,
                                          Object... uriVariables) {
        HttpEntity<String> entity = new HttpEntity<>(addHeaders(req, res));
        try {
            return restTemplate.exchange(BASE_URL + additionUrl, method, entity, responseType, uriVariables);
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, req, res);
            return null;
        }
    }


    public <T> T postForObject(HttpServletRequest req, HttpServletResponse res, String additionUrl,
                               T requestBody,
                               Class<T> responseType) {
        HttpEntity<T> entity = new HttpEntity<>(requestBody, addHeaders(req, res));
        try {
            return restTemplate.postForObject(BASE_URL + additionUrl, entity, responseType);
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, req, res);
            return null;
        }

    }

    public <T> T patchForObject(HttpServletRequest req, HttpServletResponse res, String additionUrl,
                                T requestBody,
                                Class<T> responseType) {
        HttpEntity<T> entity = new HttpEntity<>(requestBody, addHeaders(req, res));
        try {
            return restTemplate.patchForObject(BASE_URL + additionUrl, entity, responseType);
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, req, res);
            return null;
        }
    }


    //остальные методы так же

    private HttpHeaders addHeaders(HttpServletRequest req, HttpServletResponse res) {
        HttpHeaders headers = new HttpHeaders();
        String token = AuthThreadLocalTO.getAuth();
        if (token == null) {
            Enumeration<String> headerNames = req.getHeaderNames();

            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    headers.add(headerName, req.getHeader(headerName));
                }
            }
            String Text = HttpStatus.UNAUTHORIZED.getReasonPhrase();
            AuthService.sendRedirectIfError((HttpStatusCodeException) new RestClientResponseException(Text, HttpStatus.UNAUTHORIZED.value(), Text, headers, null, null), req, res);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }
}
