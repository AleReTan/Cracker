package com.netcracker.demo.models;

public class AuthThreadLocalTO {
    private static  final ThreadLocal<String> authThreadLocal = new ThreadLocal<String>();;

    public static void setAuth(String data) {
        authThreadLocal.set(data);
    }

    public static String getAuth() {
    return authThreadLocal.get();
    }

    public static void remove() {
        authThreadLocal.remove();
    }
}
