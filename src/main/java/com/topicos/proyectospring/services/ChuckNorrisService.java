package com.topicos.proyectospring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.topicos.proyectospring.models.ChuckNorrisJoke;

@Service
public class ChuckNorrisService {

    private final WebClient webClient;

    public ChuckNorrisService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.chucknorris.io")
                .build();
    }

    public Mono<String> getRandomJoke() {
        return webClient.get()
                .uri("/jokes/random")
                .retrieve()
                .bodyToMono(ChuckNorrisJoke.class)
                .map(ChuckNorrisJoke::getValue);
    }
}

