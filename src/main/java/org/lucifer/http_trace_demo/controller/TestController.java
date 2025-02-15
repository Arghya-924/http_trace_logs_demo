package org.lucifer.http_trace_demo.controller;

import org.lucifer.http_trace_demo.model.RequestAndResponseBodyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/helloWorld")
    public ResponseEntity<String> helloWorld(
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/postMapping")
    public ResponseEntity<RequestAndResponseBodyDto> postMapping(@RequestBody RequestAndResponseBodyDto body) {

        return ResponseEntity.ok(body);
    }
}
