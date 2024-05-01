package com.havryliuk.test.users.util;

public class HttpReasonResolver {
    public static String getReasonPhrase(int statusCode) {
        return switch (statusCode) {
            case 200 -> "OK";
            case 201 -> "Created";
            case 301 -> "Moved Permanently";
            case 304 -> "Not Modified";
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 409 -> "Conflict";
            case 410 -> "Gone";
            case 500 -> "Internal Server Error";
            default -> "";
        };
    }
}
