package com.tencent.wxcloudrun.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OpenAIController {

    @Value("${openai.api.key}")
    private String openAIApiKey;

    private final String openAIEndpoint = "https://api.openai.com/v1/engines/davinci-codex/completions";

    @PostMapping("/openai/complete")
    public ResponseEntity<String> complete(@RequestBody String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        String requestBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAIApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(openAIEndpoint, request, String.class);

        return response;
    }

    @PostMapping("/test")
    public String test(){

        return "success";
    }
}
