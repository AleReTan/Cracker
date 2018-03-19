package com.netcracker.demo.utility;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static final String COOKIE_NAME = "AUTH";
    public static final String LOCALHOST = "localhost";

        public static void create(HttpServletResponse httpServletResponse, String name, String value, Integer maxAge, String domain) {
            boolean secure = true;
            if (domain.equals(LOCALHOST)){
                secure = false;
            }

            Cookie cookie = new Cookie(name, value);
            cookie.setDomain(domain);
            cookie.setPath("/");
            cookie.setSecure(secure);
            cookie.setHttpOnly(true);

            if(maxAge > 0){
               //-1 значит куки действуют до закрытия браузера
                cookie.setMaxAge(maxAge);
            }

            httpServletResponse.addCookie(cookie);
        }

        public static void clear(HttpServletResponse httpServletResponse, String name) {
            Cookie cookie = new Cookie(name, null);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            cookie.setDomain("localhost");
            httpServletResponse.addCookie(cookie);
        }

        public static String getValueByName(HttpServletRequest httpServletRequest, String name) {
            Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
            return cookie != null ? cookie.getValue() : null;
        }
    }

