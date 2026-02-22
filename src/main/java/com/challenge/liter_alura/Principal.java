package com.challenge.liter_alura;

import com.challenge.liter_alura.dto.DadosResposta;
import com.challenge.liter_alura.service.LivroService;
import util.ConsumoApi;
import util.ConverteDados;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private LivroService servico;

    public Principal(LivroService service) {
        this.servico = service;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    servico.listarLivrosRegistrados();
                    break;
                case 3:
                    servico.listarAutoresRegistrados();
                    break;
                case 4:
                    System.out.println("Digite o ano para listar autores vivos:");
                    int ano = leitura.nextInt();
                    leitura.nextLine();
                    servico.listarAutoresVivosEmAno(ano);
                    break;
                case 5:
                    System.out.println("Digite o idioma para listar livros ex: en, pt, es:");
                    String idioma = leitura.nextLine();
                    servico.listarLivrosPorIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroWeb() {
        DadosResposta dados = getDadosLivro();
        if (dados != null && !dados.livros().isEmpty()) {
            // Enviamos o primeiro livro da lista para o Service tratar e salvar
            servico.salvarLivro(dados.livros().get(0));
        } else {
            System.out.println("Livro não encontrado.");
        }
    }


    private DadosResposta getDadosLivro() {
        System.out.println("Digite o nome do livro para busca:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        return conversor.converter(json, DadosResposta.class);
    }
}

