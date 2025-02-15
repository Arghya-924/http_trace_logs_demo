package org.lucifer.http_trace_demo.repository.trace;

import org.lucifer.http_trace_demo.config.trace.ContentTrace;
import org.lucifer.http_trace_demo.config.trace.ContentTraceManager;
import org.lucifer.http_trace_demo.model.HttpTraceLogs;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomHttpTraceRepository implements HttpExchangeRepository {

    private final HttpTraceLogsRepository httpTraceLogsRepository;

    private final ContentTraceManager contentTraceManager;

    public CustomHttpTraceRepository(HttpTraceLogsRepository httpTraceLogsRepository, ContentTraceManager contentTraceManager) {
        this.httpTraceLogsRepository = httpTraceLogsRepository;
        this.contentTraceManager = contentTraceManager;
    }

    @Override
    public void add(HttpExchange httpExchange) {

        ContentTrace contentTrace = contentTraceManager.getContentTrace();
        contentTrace.setHttpTrace(httpExchange);

        HttpTraceLogs httpTraceLogs = mapContentTraceToHttpTraceLogs(contentTrace);

        httpTraceLogsRepository.save(httpTraceLogs);
    }

    private HttpTraceLogs mapContentTraceToHttpTraceLogs(ContentTrace contentTrace) {
        HttpTraceLogs httpTraceLogs = new HttpTraceLogs();
        httpTraceLogs.setRequestBody(contentTrace.getRequestBody());
        httpTraceLogs.setResponseBody(contentTrace.getResponseBody());
        httpTraceLogs.setRequestParams(contentTrace.getRequestParam());

        return httpTraceLogs;
    }

    @Override
    public List<HttpExchange> findAll() {
        return List.of();
    }
}
