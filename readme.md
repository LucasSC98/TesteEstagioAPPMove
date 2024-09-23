

Teste de Cadastro e Listagem de Usuários

Descrição

Este projeto é uma API REST desenvolvida com Spring Boot para realizar operações básicas de cadastro e listagem de usuários. A API permite cadastrar novos usuários e listar usuários com opções de filtragem e paginação.


Funcionalidades

Cadastro de Usuários: Permite criar novos usuários com validações para nome e e-mail. O ID do usuário é gerado automaticamente.

Listagem de Usuários: Permite listar usuários com filtros por nome e e-mail, e com paginação de resultados.

Tecnologias Utilizadas

Spring Boot: Framework para desenvolvimento de aplicações Java.

Apache Commons CSV: Biblioteca para leitura de arquivos CSV.

JPA/Hibernate: Para mapeamento objeto-relacional e operações com banco de dados.

MySQL: Banco de dados relacional.

Estrutura do Projeto

Controller: Define os endpoints da API para cadastro e listagem de usuários.

Service: Contém a lógica de negócios, incluindo validações e operações de banco de dados.

Repository: Interface para acesso a dados e consultas ao banco de dados.

Model: Entidade do usuário que representa a tabela no banco de dados.

DTO: Data Transfer Object para transferir dados de usuário.

Config: Configurações e serviços auxiliares, como a importação de dados de CSV.

Endpoints da API

Cadastro de Usuário

URL: /api/usuarios

Método: POST

curl -X POST "http://localhost:8081/api/usuarios" -H "Content-Type: application/json" -d "{\"name\": \"Lucas Silva\", \"email\": \"lucas.silva@eggg.com\"}"

Respostas:
201 Created: Usuário criado com sucesso.

400 Bad Request: Dados inválidos.

Listagem de Usuários

URL: /api/usuarios
Método: GET

curl -X GET "http://localhost:8080/api/usuarios?name=Lucas&page=0&size=20"

Parâmetros de Consulta:
name: (opcional) Nome do usuário para filtragem.

email: (opcional) Email do usuário para filtragem.
>curl -X GET "http://localhost:8080/api/usuarios?email=lucas.silva@eggg.com&page=0&size=20"

page: (opcional) Número da página (começa em 0).

size: (opcional) Número de registros por página (padrão: 20).

Respostas:
200 OK: Lista de usuários filtrados e paginados.

Como executar o teste:

A API estará disponível em http://localhost:8081.
Testes
Para testar os endpoints, você pode usar ferramentas como Postman ou curl. Exemplos de comandos curl são fornecidos abaixo para referência.

Observações

Validações: As validações são feitas usando anotações JSR-380 (Bean Validation).

Importação de Dados: Existe uma funcionalidade para importar dados de um arquivo CSV, Apache Commons CSV.

Eu tentei Importar todos 10 milhões de Usuários mas não consegui, o computador trava ou nem começa importar, Teste os recursos com 10 usuários para verificar se estava funcionando corretamente.

Para desativar a importação automática, você pode remover o componente DataLoader ou comentar o método importarUsuarios no ImportacaoService.

