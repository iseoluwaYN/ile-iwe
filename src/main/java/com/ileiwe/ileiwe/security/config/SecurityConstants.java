package com.ileiwe.ileiwe.security.config;

public class SecurityConstants {
    public static final String SECRET = "SecurityKeyToGensJWT";
    public static final long EXPIRATION_TIME = 864_000_00; //10 days
    public static final String  TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
