# Sistema de Conta Bancária - Backend

Este é um sistema de conta bancária simples desenvolvido em Java, que utiliza um banco de dados MySQL para armazenar informações dos usuários e seus saldos.

## Funcionalidades

- Criar uma conta bancária
- Fazer login em uma conta existente
- Adicionar saldo à conta
- Extrair saldo da conta

## Tecnologias Utilizadas

- Java
- MySQL
- JDBC (Java Database Connectivity)

## Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em seu ambiente:

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J (biblioteca JDBC para MySQL)

## Configuração

1. Clone este repositório:

2. Importe o projeto em sua IDE Java de preferência (Eclipse, IntelliJ, etc.).

3. Importe o banco de dados `banco.sql` para o seu servidor MySQL. Este arquivo contém a estrutura da tabela `usuariosbanco`.

4. Atualize as configurações de conexão com o banco de dados no arquivo `Conection.java`, se necessário.

5. Compile e execute o projeto.

## Uso

Após iniciar o projeto, você será apresentado com a tela inicial. A partir daí, você pode escolher entre criar uma nova conta ou fazer login em uma conta existente.

Ao fazer login, você poderá adicionar saldo à sua conta ou extrair saldo dela.

## Contribuições

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

