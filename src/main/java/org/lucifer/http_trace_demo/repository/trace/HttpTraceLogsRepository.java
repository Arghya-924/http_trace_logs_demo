package org.lucifer.http_trace_demo.repository.trace;

import org.lucifer.http_trace_demo.model.HttpTraceLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpTraceLogsRepository extends JpaRepository<HttpTraceLogs, Long> {
}
