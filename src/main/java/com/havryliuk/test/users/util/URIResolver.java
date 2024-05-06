package com.havryliuk.test.users.util;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Map;

public class URIResolver {
    static String getWithParameters(WebRequest request) {
        StringBuilder builder = new StringBuilder();
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        builder.append(servletWebRequest.getRequest().getRequestURI());
        Map<String, String[]> parameterMap = servletWebRequest.getRequest().getParameterMap();
        if(!parameterMap.isEmpty()) {
            builder.append("?");
        }
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append("=").append(Arrays.toString(entry.getValue())).append("&");
        }
        if(!parameterMap.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString()
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }
}
