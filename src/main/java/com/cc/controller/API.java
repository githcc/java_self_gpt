package com.cc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("v1")
public class API {

    private final WebClient webClient;

    public API(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai-proxy.com").build();
    }

    @PostMapping(value = "/chat/completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStream(@RequestBody String body) {
        HttpHeaders headers = new HttpHeaders();
        // Set your desired headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer <key>");
        headers.set("content-type", "application/json");

        return webClient.post()
                .uri("/v1/chat/completions")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToFlux(String.class);
    }
}