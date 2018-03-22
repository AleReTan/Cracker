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
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {

        String requestURI = req.getRequestURI();
        String cookieAuth = CookieUtil.getValueByName(req, CookieUtil.COOKIE_NAME);
        if (cookieAuth != null) {
            AuthThreadLocalTO.setAuth(cookieAuth);
        } else {
            if (!requestURI.equals("/login")) {
                res.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView modelAndView) throws Exception {
        //System.out.println("SiteInterceptor postHandle for url" + req.getRequestURI());

        int httpStatus = res.getStatus();

        if (httpStatus != HttpStatus.OK.value()) {

            if (!req.getRequestURI().equals("/errorMSG")&&!req.getRequestURI().equals("/error")){

                if (modelAndView != null) {
                    modelAndView.clear();
                }

                if (httpStatus == HttpStatus.UNAUTHORIZED.value()) {
                    CookieUtil.clear(req,res, CookieUtil.COOKIE_NAME, req.getServerName());
                    res.sendRedirect("/login");
                    return;
                }

                req.getRequestDispatcher("/errorMSG").forward(req, res);
                return;
            }
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object o, Exception e) throws Exception {
        // System.out.println("SiteInterceptor afterCompletion");
        //AuthThreadLocalTO.remove();
    }
}
