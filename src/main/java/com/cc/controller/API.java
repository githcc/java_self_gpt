package com.cc.controller;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("v1")
public class API {

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    private static final String OPENAI_API_KEY = "Bearer YOUR_API_KEY_HERE";
    private static final String OPENAI_API_URL = "https://api.openai-proxy.com/v1";

    public API(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.baseUrl(OPENAI_API_URL).build();
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/chat/completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStream(@RequestBody String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", OPENAI_API_KEY);
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);

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
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return this.restTemplate.exchange(
                OPENAI_API_URL + "/images/generations",
                HttpMethod.POST,
                request,
                String.class
        );
    }

    @PostMapping(value = "/audio/transcriptions")
    public ResponseEntity<String> transcribeAudio(@RequestPart("file") MultipartFile file, @RequestParam("model") String model, @RequestParam("temperature") String temperature
            , @RequestParam("response_format") String response_format, @RequestParam("language") String language) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", OPENAI_API_KEY);
        headers.set("content-type", MediaType.MULTIPART_FORM_DATA_VALUE);
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", file.getResource());
        requestBody.add("model", model);
        requestBody.add("temperature", temperature);
        requestBody.add("response_format", response_format);
        requestBody.add("language", language);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(requestBody, headers);

        return this.restTemplate.exchange(
                OPENAI_API_URL + "/audio/transcriptions",
                HttpMethod.POST,
                request,
                String.class
        );
    }

    @PostMapping(value = "/audio/speech")
    public ResponseEntity<byte[]> tts(@RequestBody String body) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", OPENAI_API_KEY);
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return this.restTemplate.exchange(
                OPENAI_API_URL + "/audio/speech",
                HttpMethod.POST,
                request,
                byte[].class
        );
    }
}