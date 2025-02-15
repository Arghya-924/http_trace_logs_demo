package org.lucifer.http_trace_demo.config.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentTrace {

    private HttpExchange httpTrace;
    private String requestBody;
    private String responseBody;
    private String requestParam;
}
