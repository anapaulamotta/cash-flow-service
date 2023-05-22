# Cash Flow Service

Projeto de um serviço de fluxo de caixa que permite adicionar transações e gerar relatórios diários com o total consolidado.

## Tecnologias utilizadas

- Java 11
- Spring Boot 2.6.7
- PostgreSQL
- Hibernate
- Lombok
- MapStruct
- OpenCSV

## Configuração 
### banco de dados
O projeto utiliza o PostgreSQL como banco de dados. Para configurar a conexão com o banco, é necessário editar o arquivo `application.properties`
Certifique-se de substituir `localhost:5432` pelo endereço e porta corretos do seu banco de dados, e `postgres` pelo nome de usuário e senha corretos.
### execução
Para executar o projeto, é necessário ter o Java 11 e o Maven instalados.

## Endpoints disponíveis

### Adicionar transação

POST /transactions/{type}

Este endpoint permite adicionar uma transação ao sistema. O parâmetro `{type}` deve ser substituído pelo tipo da transação: "credit" ou "debit". O corpo da requisição deve conter uma lista de objetos `TransactionDTO` com as informações com a descrição e o valor. Os valores devem estar positivos;

Exemplo:

```json
POST /transactions/credit

[
  {
    "description": "Venda de produtos",
    "value": 1500.0
  },
  {
    "description": "Aluguel da garagem",
    "value": 800.0
  }
]
```

### Gerar relatório
GET /transactions/report
Este endpoint permite gerar um relatório das transações registradas. É possível especificar um intervalo de datas para o relatório utilizando os parâmetros initialDate e finalDate no formato "dd/MM/yyyy". Caso nenhum intervalo seja fornecido, o relatório será gerado com as transações do dia atual.

Exemplo de requisição:
```json
GET /transactions/report?initialDate=01/05/2023&finalDate=31/05/2023
```


