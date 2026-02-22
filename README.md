📚 LiterAlura - Catálogo de Livros
O LiterAlura é um desafio de programação que consiste em desenvolver um catálogo de livros que oferece interação textual (via console) com os usuários. O projeto consome dados da API Gutendex, armazena as informações em um banco de dados relacional e permite diversas consultas e filtragens.

🛠️ Tecnologias Utilizadas
Java 17/21

Spring Boot 3+

Spring Data JPA

PostgreSQL (Banco de dados)

Jackson (Manipulação de JSON)

Maven (Gerenciador de dependências)

🎯 Funcionalidades
O sistema oferece as seguintes opções no menu principal:

Buscar livro pelo título: Pesquisa na API Gutendex e salva o livro e seu autor no banco de dados.

Listar livros registrados: Exibe todos os livros que já foram salvos localmente.

Listar autores registrados: Lista todos os autores salvos no sistema.

Listar autores vivos em um determinado ano: Filtra autores que estavam vivos no ano informado pelo usuário.

Listar livros em um determinado idioma: Filtra livros salvos por sigla de idioma (ex: en, pt, es).

🌟 Funcionalidades Extras (Diferenciais)
📊 Gerando Estatísticas: Exibe a média, o valor máximo, mínimo e a contagem de downloads de todos os livros no banco, utilizando a classe DoubleSummaryStatistics.

🏆 Top 10 Livros: Uma consulta otimizada que retorna os 10 livros mais populares registrados no sistema.

🔍 Busca de Autor por Nome: Busca específica no banco de dados para localizar informações de autores já cadastrados.

🚀 Como Executar o Projeto
Pré-requisitos
JDK 17 ou superior.

PostgreSQL instalado e rodando.

IDE (IntelliJ IDEA recomendada).
