package com.netcracker.demo.models;

public class AuthThreadLocalTO {

    private static  final ThreadLocal authThreadLocal = new ThreadLocal();

    public static <T> void  setAuth(T data) {
        authThreadLocal.set(data);
    }

    public static <T> T getAuth() {
    return (T)authThreadLocal.get();
    }

    public static void remove() {
        authThreadLocal.remove();
    }
}
