package com.challenge.liter_alura.service;

import com.challenge.liter_alura.dto.DadosLivro;
import com.challenge.liter_alura.entity.Autor;
import com.challenge.liter_alura.entity.Livro;
import com.challenge.liter_alura.repository.AutorRepository;
import com.challenge.liter_alura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
        } else {
            System.out.println("\n--- AUTORES REGISTRADOS ---");
            autores.forEach(a ->
                    System.out.println("Autor: " + a.getNome() +
                            " (" + a.getAnoNascimento() + " - " +
                            (a.getAnoFalecimento() != null ? a.getAnoFalecimento() : "Presente") + ")")
            );
        }
    }

    public void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();

        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    public void salvarLivro(DadosLivro dadosLivro) {
        try {
            if (livroRepository.findByTituloContainingIgnoreCase(dadosLivro.titulo()).isPresent()) {
                System.out.println("Erro: Este livro já está cadastrado no banco de dados.");
                return;
            }

            // 2. Tratar o autor (Verificar se já existe ou criar novo)
            var dadosAutor = dadosLivro.autores().get(0); // Pegando o primeiro autor
            Autor autor = autorRepository.findByNomeContainingIgnoreCase(dadosAutor.nome())
                    .orElseGet(() -> autorRepository.save(new Autor(dadosAutor)));

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

    public void listarAutoresVivosEmAno(int ano) {
        List<Autor> autores = autorRepository.buscarAutoresVivosNoAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano de " + ano);
        } else {
            autores.forEach(System.out::println);
        }
    }

    public void listarLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.findByIdiomaContainingIgnoreCase(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma: " + idioma);
        } else {
            livros.forEach(System.out::println);
        }
    }

    public void listarTop10LivrosMaisBaixados() {
        System.out.println("\n--- TOP 10 LIVROS MAIS BAIXADOS ---");
        List<Livro> top10 = livroRepository.findTop10ByOrderByNumeroDownloadsDesc();
        top10.forEach(l ->
                System.out.println(l.getTitulo() + " - Downloads: " + l.getNumeroDownloads()));
    }

    public void exibirEstatisticasDownloads() {
        List<Livro> livros = livroRepository.findAll();
        DoubleSummaryStatistics est = livros.stream()
                .filter(l -> l.getNumeroDownloads() > 0)
                .collect(Collectors.summarizingDouble(Livro::getNumeroDownloads));

        System.out.println("\n--- ESTATÍSTICAS DE DOWNLOADS ---");
        System.out.println("Média de downloads: " + String.format("%.2f", est.getAverage()));
        System.out.println("Máximo de downloads: " + est.getMax());
        System.out.println("Mínimo de downloads: " + est.getMin());
        System.out.println("Quantidade de livros avaliados: " + est.getCount());
    }

    public void buscarAutorPorNome(String nomeAutor) {
        var autorOpt = autorRepository.findByNomeContainingIgnoreCase(nomeAutor);
        if (autorOpt.isPresent()) {
            Autor autor = autorOpt.get();
            System.out.println("\n--- AUTOR ENCONTRADO ---");
            System.out.println(autor);
            if (autor.getLivros().isEmpty()) {
                System.out.println("Este autor não tem livros registrados.");
            } else {
                System.out.println("Livros deste autor:");
                autor.getLivros().forEach(l -> System.out.println("- " + l.getTitulo()));
            }
        } else {
            System.out.println("Nenhum autor encontrado com o nome: " + nomeAutor);
        }
    }
}
