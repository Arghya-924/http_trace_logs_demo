package org.lucifer.http_trace_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAndResponseBodyDto {

    private String firstName;
    private String lastName;
    private String email;
}
