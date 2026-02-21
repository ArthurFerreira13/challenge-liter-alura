package com.challenge.liter_alura.service;

import tools.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converter(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON: " + e.getMessage(), e);
        }
    }
}
