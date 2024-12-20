package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Enumeration;

@Component
@Slf4j
public class LoggerFilter implements Filter {


    @Getter
    enum IN_OUT{
        IN("Request >>>>"), OUT("Response <<<<");

        final String prefix;
        IN_OUT(String prefix){
            this.prefix = prefix;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        String uri = req.getRequestURI();
        String method = req.getMethod();

        // Request Info
        logRequestInfo(req, uri, method);

        // Response Info
        logResponseInfo(res, uri, method);

        res.copyBodyToResponse();
    }

    private void printLogger(IN_OUT inOut, String uri, String method, String header, String body) {

        log.info("{} uri : {}, method : {}, header : {} \r\nbody : {}", inOut.getPrefix(), uri, method, header, body);
    }

    private void logRequestInfo(ContentCachingRequestWrapper req, String uri, String method) {
        Enumeration<String> requestHeaders =  req.getHeaderNames();
        StringBuilder headerValues = new StringBuilder();

        requestHeaders.asIterator().forEachRemaining(headerKey -> {
            String headerValue = req.getHeader(headerKey);
            headerValues
                    .append("[ ")
                    .append(headerKey)
                    .append(": ")
                    .append(headerValue)
                    .append(" ]");
        });

        String requestBody = new String(req.getContentAsByteArray());
        printLogger(IN_OUT.IN, uri, method, headerValues.toString(), requestBody);

    }

    private void logResponseInfo(ContentCachingResponseWrapper res, String uri, String method) {
        StringBuilder responseHeaderValue = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            String headerValue = res.getHeader(headerKey);
            responseHeaderValue
                    .append("[ ")
                    .append(headerKey)
                    .append(": ")
                    .append(headerValue)
                    .append(" ]");
        });

        String responseBody = new String(res.getContentAsByteArray());
        printLogger(IN_OUT.OUT, uri, method, responseHeaderValue.toString(), responseBody);
    }
}
