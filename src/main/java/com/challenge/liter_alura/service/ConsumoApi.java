package com.challenge.liter_alura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    public String obterDados(String endereco) {
        // Criamos o cliente que vai fazer a requisição
        HttpClient client = HttpClient.newHttpClient();

        // Montamos a requisição com a URL (endereço)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;
        try {
            // Enviamos a requisição e recebemos a resposta como String (JSON)
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao conectar com a API: " + e.getMessage());
        }

        return response.body();
    }
}
