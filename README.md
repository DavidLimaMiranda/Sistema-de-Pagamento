
## Tecnologias usadas

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

# Descrição do projeto

## do que se trata?

Esté projeto é um desafio que encontrei do pic-pay para desenvolvedores back-end. Neste projeto utilizei o Java com Spring-boot e banco de dados em memoria H2.

## Principal objetivo

Fiz esse projeto com intuito de lapidar meus conhecimentos em Java e aprender como seria programar uma transação de compra entre clientes e lojas.

# Como rodar o projeto

Primeiro passo seria baixar o zip do projeto e abrir a pasta utilizando IDE do Intellij ou Visual Studio code.Para rodar o projeto clique na classe: TransacoesApplication e clique no botão run (atalho SHIFT+f10).

Com que sejá possível efetuar as requisições é necessario ter algum programa que realize requisições HTTPs como o Postman ou insomnia.

# Rotas

## Rotas do cliente

Rota do tipo GET para listar todos os clientes:
```
http://localhost:8080/clientes
```

Rota para cadastar um cliente é igual porem do tipo POST:
```
http://localhost:8080/clientes
```

Formato da requisição:

```
{
    "firstName": "David",
    "lastName": "Lima",
    "balance": 40,
    "email": "david222222@gmail.com",
    "documento": "1234567812222",
    "clientType": "MERCHANT" ou "COMMON"
}
```
## Sobre clientType

Existem 2 tipos de clientType sendo "**MERCHANT**" para representar empresas e lojas que recebem pagamentos e "**COMMON**" que seria clientes comuns.

Clientes do tipo merchant não podem realizar transferencias apenas recebelas, enquanto clientes do tipo common podem realizar ambas.

## Rotas de transação

Rota do tipo POST para efetuar uma transação

```
http://localhost:8080/transactions
```
Formato da requisição: 
```
{
    "senderId": 1, <-- Id do cliente que enviara o dinheiro
    "receiverId": 2, <-- Id do cliente que receberá o dinheiro
    "value": 10 <-- valor da transação
}
```
