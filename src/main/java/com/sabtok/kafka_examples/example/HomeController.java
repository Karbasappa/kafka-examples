package com.sabtok.kafka_examples.example;


import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class HomeController {

    @PostMapping("/save")
    public void save(@RequestBody Map<String, String> paylosd) {
        System.out.println(paylosd);
    }

    @GetMapping("/getall")
    public Map getD(){
        Map<String, String> data = new LinkedHashMap<>();
        data.put("name", "Varun");
        data.put("email", "xyz@gamil.com");
        data.put("password", "abc");
        return data;
    }
}
