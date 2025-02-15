package org.lucifer.http_trace_demo.config.trace.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.Include;
import org.springframework.boot.actuate.web.exchanges.reactive.HttpExchangesWebFilter;
import org.springframework.boot.actuate.web.exchanges.servlet.HttpExchangesFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomHttpTraceFilter extends HttpExchangesFilter {

    private final String getpath = "/test/helloWorld";
    private final String postPath = "/test/postMapping";

    /**
     * Create a new {@link HttpExchangesWebFilter} instance.
     *
     * @param repository the repository used to record events
     * @param includes   the include options
     */
    public CustomHttpTraceFilter(HttpExchangeRepository repository, Set<Include> includes) {
        super(repository, includes);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        List<String> listOfApis = new ArrayList<>();
        listOfApis.add(getpath);
        listOfApis.add(postPath);

        return !listOfApis.contains(request.getServletPath());
    }
}
