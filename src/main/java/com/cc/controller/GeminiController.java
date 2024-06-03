package com.cc.controller;

import com.cc.generator.domain.Request;
import com.cc.generator.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GeminiController {
    private final WebClient webClient;
    private final RestTemplate restTemplate;

    @Autowired
    private RequestService requestService;
    private static final String GEMINI_API_KEY = "key";
    private static final String GEMINI_API_URL = "https://api.genai.gd.edu.kg/google/v1";

    public GeminiController(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.baseUrl(GEMINI_API_URL).build();
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/models/gemini-pro:generateContent")
    public ResponseEntity<String> generateContent(@RequestBody String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        Request requestContent = new Request();
        requestContent.setMapping("/models/gemini-pro:generateContent");
        requestContent.setContentText(body);
        requestService.saveOrUpdate(requestContent);

        return this.restTemplate.exchange(
                GEMINI_API_URL + "/models/gemini-pro:generateContent?key="+GEMINI_API_KEY,
                HttpMethod.POST,
                request,
                String.class
        );
    }


    @PostMapping(value = "/models/models/{model}:streamGenerateContent?alt=sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamGenerateContent(@RequestBody String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("X-Goog-Api-Key", GEMINI_API_KEY);
        Request requestContent = new Request();
        requestContent.setMapping("models/gemini-pro:streamGenerateContent?alt=sse");
        requestContent.setContentText(body);
        requestService.saveOrUpdate(requestContent);

        return webClient.post()
                .uri("/models/gemini-pro:streamGenerateContent?key="+GEMINI_API_KEY)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToFlux(String.class);
    }
}
