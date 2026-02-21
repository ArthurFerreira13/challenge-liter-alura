package com.challenge.liter_alura;

import com.challenge.liter_alura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private LivroService service;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(service);
        principal.exibeMenu();
    }
}
