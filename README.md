# http_trace_logs_demo

# This is a Demo project for storing Http Trace Logs in database

This is achieved by using Spring's HttpTraceRepository.
We have to implement this interface and store the request and responses manually in the database.

For getting the request and responses on the fly we are using the power of Filters 
to wrap the request and responses using **ContentCachingRequestWrapper** and **ContentCachingResponseWrapper**

**Note** : For this demo I am using H2 database, and I am logging only the requestBody, responseBody and the requestParams 
for only 2 Api's.

This behaviour is customizable for more API's by adding the list of API's in the **shouldNotFilter** method of
**CustomHttpTraceFilter**.