package com.challenge.liter_alura.service;

public interface IConverteDados {
    <T> T converter(String json, Class<T> classe);
}
