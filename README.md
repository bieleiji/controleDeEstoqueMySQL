# Sistema de Controle de Estoque #

Sistema desenvolvido em Java para gerenciamento de produtos com controle de estoque.

## 

## Funcionalidades
- Cadastro de produtos
- Listagem de produtos
- Atualização de dados
- Remoção de produtos
- Reposição de estoque
- Retirada de estoque com validação
- Tratamento de erros sem interromper o sistema

## Regras de negócio
1. Não permite estoque negativo
2. Valida existência do produto antes de operações
3 Controle de entrada e saída de produtos

## Tecnologias utilizadas
* Java
* JDBC
* MySQL

## Estrutura do projeto
- model/      → entidades (Produto)
- service/    → regras de negócio
- dao/        → acesso ao banco
- main/       → interface via terminal

## Como executar
1. Criar o banco no MySQL
2. Configurar usuário e senha no código
3. Executar a classe principal

## Aprendizados
- Integração Java + banco de dados
- Uso de JDBC
- Estruturação em camadas (DAO / Service / Model)
- Implementação de regras de negócio reais
