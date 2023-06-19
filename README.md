# *internet-banking-ficticio*
Desafio Técnico de um Internet Banking Fictício, com API Rest e base em memória

# Premissas do Desafio

## Premissas de Negócio

O tema do projeto fictício é um Internet Banking, o projeto precisa
ter as seguintes funcionalidades:

1. Cadastrar um cliente que contenha os atributos abaixo:

| Nome do Campo      | Tipo           |
|--------------------|----------------|
| Nome               | String         |
| Plano exclusive?   | Boolean        |
| Saldo              | BigDecimal     |
| Número da conta    | String         |
| Data de nascimento | Date           |

2. Retornar todos clientes cadastrados

3. Sacar um valor que subtrai o saldo do cliente contando uma taxa de
   administração conforme a tabela abaixo:

| Valor                         | Taxa de administração   |
|-------------------------------|-------------------------|
| VALOR <= 100,00               | Isento de taxa de saque |
| VALOR > 100,00 E VALOR <= 300 | Taxa de 0.4%            |
| VALOR > 300,00                | Taxa de 1%              |
| Plano exclusive?              | Isento de taxa de saque |

4. Depositar um valor que aumenta o saldo de um determinado cliente
5. Consultar o histórico de transações de cada movimentação por Data
   (Saque e depósito)


## Premissas Tecnológicas

- Utilizar a dependência h2 como banco de dados em memória;
- Fornecer carga prévia de dados junto ao projeto;
- O design da api deve contemplar minimamente a maturidade de
  Richardson no nível 2.

<br>


#  Stack de Tecnologias
- **Java 17** - pré-requisito para execução;
- **Maven** - como ferramenta de gerenciamento de dependências e empacotamento;
- **Spring Boot** - como Framework para autoconfiguração da aplicação e injeção de dependências;
- **H2 Database** - como base de dados para execução local (em memória);
- **JPA com Hibernate** - como Framework para integração ao banco de dados;
- **TODO** - outras tecnologias e frameworks utilizados.

<br>


# Guia para execução local da aplicação
A aplicação Java presente neste projeto encontra-se dentro do diretório `app/`.

Além da configuração do Java em máquina local, a aplicação não possui nenhum outro pré-requisito de configuração.
Basta compilar, utilizando Maven, e executar a classe:

[app/src/main/java/internetbankingficticio/InternetBankingFicticioApplication.java](app/src/main/java/internetbankingficticio/InternetBankingFicticioApplication.java)

## Utilizando a API disponível
### Interface de usuário da API usando Swagger
Ao subir a aplicação, a interface do Swagger para utilização da API fica disponível em:

http://localhost:8080/internetbanking-api-ui.html

### Documentação da API usando Open API Docs
Ao subir a aplicação, a documentação da API fica disponivel em:

http://localhost:8080/internetbanking-api-docs

### Coleção Postman das chamadas da API
Há também uma collection Postman anexada a este repositório, no seguinte caminho:

[docs/postman-collections/Internet Banking Ficticio.postman_collection.json](docs/postman-collections/Internet Banking Ficticio.postman_collection.json)

## Acessando o banco de dados em memória
Ao subir a aplicação, a interface para a base em memória fica disponível em:

http://localhost:8080/internetbanking-db-ui/

Usuário e senha da base local podem ser verificados / alterados dentro do arquivo abaixo, dentro das configs de `spring.datasource.username` e `spring.datasource.password`

[app/src/main/resources/application.yaml](app/src/main/resources/application.yaml)


<br>
<hr>