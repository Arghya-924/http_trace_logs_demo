package org.lucifer.http_trace_demo.config.trace.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lucifer.http_trace_demo.config.trace.ContentTraceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContentTraceFilter extends OncePerRequestFilter {

    private final String getpath = "/test/helloWorld";
    private final String postPath = "/test/postMapping";

    private final ContentTraceManager contentTraceManager;

    @Autowired
    public ContentTraceFilter(ContentTraceManager contentTraceManager) {
        this.contentTraceManager = contentTraceManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
            contentTraceManager.updateBody(requestWrapper, responseWrapper);
        } finally {
            responseWrapper.copyBodyToResponse();
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        List<String> listOfApis = new ArrayList<>();
        listOfApis.add(getpath);
        listOfApis.add(postPath);

        return !listOfApis.contains(request.getServletPath());
    }
}
