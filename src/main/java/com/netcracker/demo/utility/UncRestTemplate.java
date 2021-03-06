package com.netcracker.demo.utility;


import com.netcracker.demo.models.AuthThreadLocalTO;
import com.netcracker.demo.service.AuthService;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Objects;

@Component
//@PropertySource("classpath:url.properties")
public class UncRestTemplate {
    //@Resource
    //private Environment env;

    //public static final String BASE_URL = "http://localhost:8082";
    public static final String BASE_URL = "http://185.246.65.240:8080/app";
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
            T t = restTemplate.postForObject(BASE_URL + additionUrl, entity, responseType);
            return t;

        } catch (HttpStatusCodeException e) {
            //проверяем, если пишла 500, но нет хедеа, значит причина не в полях
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR && (e.getResponseHeaders().get("Error message").get(0) != null)) {
                String errorMessage = e.getResponseHeaders().get("Error message").get(0);
                throw new IllegalArgumentException(errorMessage);
            } else {
                AuthService.sendRedirectIfError(e, req, res);
                return null;
            }
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
