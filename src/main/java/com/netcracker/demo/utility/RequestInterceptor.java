package com.netcracker.demo.utility;

import com.jayway.jsonpath.JsonPath;
import com.netcracker.demo.models.AuthThreadLocalTO;
import com.netcracker.demo.service.AuthService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

@Component

public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        Cookie cookieAuth = authService.getCookie(httpServletRequest);
        if (cookieAuth != null) {
            AuthThreadLocalTO.setAuth(authService.getToken(cookieAuth.toString()));
        } else {

            if (!requestURI.equals("/login")) {
               // httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            }
            System.out.println(">>>MyInterceptor1>>>>>>> Call before request processing （Controller Before method )" + requestURI);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

   /* static String extractPostRequestBody(HttpServletRequest request) throws IOException {
        MultiReadHttpServletRequest requestWrapper= new      MultiReadHttpServletRequest(request);
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
        BufferedReader reader = requestWrapper.getReader();
        while ((line = reader.readLine()) != null)
        jb.append(line);
        } catch (Exception e) { /*report an error*/ //}

//}


       /* try{
            // String login = JsonPath.parse(jb).read("$.login", String.class);
            //String password = JsonPath.read(jb, "$.password");
        }catch (Exception e){
            System.out.println(e.toString());}*/


      /*    if ("POST".equalsIgnoreCase(request.getMethod())) {
        Scanner s = null;
        try {
            MultiReadHttpServletRequest requestWrapper =new      MultiReadHttpServletRequest(request);
            ServletInputStream servletInputStream = request.getInputStream();
            Boolean b = servletInputStream.isReady();
            // s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.hasNext() ? s.next() : "";
    }
        return "";*/
}