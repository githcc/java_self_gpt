package com.cc.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("v1")
public class API {

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    private static final String OPENAI_API_KEY = "Bearer YOUR_API_KEY_HERE";
    private static final String OPENAI_API_URL = "https://api.openai.com/v1";

    public API(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.baseUrl(OPENAI_API_URL).build();
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/chat/completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStream(@RequestBody String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", OPENAI_API_KEY);
        headers.set("content-type", "application/json");

        return webClient.post()
                .uri("/chat/completions")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToFlux(String.class);
    }


    @PostMapping(value = "/images/generations")
    public ResponseEntity<String> generateImage(@RequestBody String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", OPENAI_API_KEY);
        headers.set("content-type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = this.restTemplate.exchange(
                OPENAI_API_URL+"/images/generations",
                HttpMethod.POST,
                request,
                String.class
        );

        return response;
    }
}