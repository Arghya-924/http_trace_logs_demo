package org.lucifer.http_trace_demo.config.trace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

@Slf4j
@Component
@RequestScope
public class ContentTraceManager {

    private ContentTrace contentTrace;

    public ContentTrace getContentTrace() {
        if (this.contentTrace == null) {
            this.contentTrace = new ContentTrace();
        }
        return this.contentTrace;
    }

    public void updateBody(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {

        String requestBody = getRequestBody(request);
        getContentTrace().setRequestBody(requestBody);

        String requestParams = getRequestParams(request);
        getContentTrace().setRequestParam(requestParams);

        String responseBody = getResponseBody(response);
        getContentTrace().setResponseBody(responseBody);
    }

    private String getRequestParams(ContentCachingRequestWrapper request) {

        String nameParam = request.getParameter("name");
        String ageParam = request.getParameter("age");

        log.info("name : {}, age : {} ", nameParam, ageParam);

        return "Name : " + nameParam + " Age " + ageParam;
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {

        try {
            if (response.getContentSize() <= 0) {
                return null;
            }
            return new String(response.getContentAsByteArray(), 0, response.getContentSize(), response.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            log.error("ContentTraceManager | getResponseBody | UnsupportedEncodingException {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("ContentTraceManager | getResponseBody | Exception {}", e.getMessage());
            return null;
        }
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {

        try {
            if (request.getContentLength() <= 0) {
                return null;
            }
            return new String(request.getContentAsByteArray(), 0, request.getContentLength(), request.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            log.error("ContentTraceManager | getRequestBody | UnsupportedEncodingException {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("ContentTraceManager | getRequestBody | Exception {}", e.getMessage());
            return null;
        }
    }
}
