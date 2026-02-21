package com.challenge.liter_alura.service;

import com.challenge.liter_alura.dto.DadosLivro;
import com.challenge.liter_alura.entity.Autor;
import com.challenge.liter_alura.entity.Livro;
import com.challenge.liter_alura.repository.AutorRepository;
import com.challenge.liter_alura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void salvarLivro(DadosLivro dadosLivro) {
        try {
            // 1. Verificar se o livro já existe pelo título para evitar duplicados
            if (livroRepository.findByTituloContainingIgnoreCase(dadosLivro.titulo()).isPresent()) {
                System.out.println("Erro: Este livro já está cadastrado no banco de dados.");
                return;
            }

            // 2. Tratar o autor (Verificar se já existe ou criar novo)
            var dadosAutor = dadosLivro.autores().get(0); // Pegando o primeiro autor
            Autor autor = autorRepository.findByNomeContainingIgnoreCase(dadosAutor.nome())
                    .orElseGet(() -> autorRepository.save(new Autor(dadosAutor)));

            // 3. Criar e salvar o livro
            Livro livro = new Livro(dadosLivro);
            livro.setAutor(autor);

            livroRepository.save(livro);
            System.out.println("Livro salvo com sucesso: " + livro.getTitulo());

        } catch (IndexOutOfBoundsException e) {
            System.err.println("Erro: A API não retornou informações de autor para este livro.");
        } catch (Exception e) {
            System.err.println("Erro inesperado ao salvar o livro: " + e.getMessage());
        }
    }
}
