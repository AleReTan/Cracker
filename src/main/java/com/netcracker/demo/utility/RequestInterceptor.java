package com.netcracker.demo.utility;

import com.netcracker.demo.models.AuthThreadLocalTO;
import com.netcracker.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //System.out.println("SiteInterceptor preHandle");
        String requestURI = httpServletRequest.getRequestURI();
        String cookieAuth = CookieUtil.getValueByName(httpServletRequest,CookieUtil.COOKIE_NAME);
        if (cookieAuth != null) {
            AuthThreadLocalTO.setAuth(cookieAuth);
        } else {
            if (!requestURI.equals("/login")) {
                //здесь могут быть проблемы...
               // httpServletRequest.getRequestDispatcher("loginPage.ftl").forward(httpServletRequest,httpServletResponse);
                      //  getRequestDispathcer().forward(request, response);
                httpServletResponse.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("SiteInterceptor postHandle for url" + httpServletRequest.getRequestURI());
        int httpStatus = httpServletResponse.getStatus();

       if (httpStatus != HttpStatus.OK.value()){
          if( !httpServletRequest.getRequestURI().equals("/errorMSG")){
           if(modelAndView!=null){
                modelAndView.clear();
           }
            httpServletRequest.getRequestDispatcher("/errorMSG").forward(httpServletRequest,httpServletResponse);
            return;}
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("SiteInterceptor afterCompletion");
        //AuthThreadLocalTO.remove();
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