# Projuris Processes Manager

## Descrição

O Projuris Processes Manager é uma aplicação Spring Boot desenvolvida para gerenciar processos jurídicos. Ele fornece APIs RESTful para realizar operações CRUD (Criar, Ler, Atualizar, Deletar) em processos, permitindo a associação de advogados a cada processo.

## Estrutura do Projeto

### Controller

A camada de controlador (ProcessesController) é responsável por expor as APIs RESTful e lidar com as requisições HTTP. As principais operações incluem:

- `POST /api/v1/processes`: Criação de um novo processo.
- `GET /api/v1/processes/{id}`: Busca de um processo pelo ID.
- `GET /api/v1/processes`: Busca de todos os processos.
- `PUT /api/v1/processes/{id}`: Atualização de um processo existente.
- `DELETE /api/v1/processes/{id}`: Deleção de um processo pelo ID.

### DTO

Os Data Transfer Objects (DTOs) são utilizados para transferir dados entre as camadas do aplicativo. Os principais DTOs são:

- `ProcessesDTO`: Representa os dados de um processo.
- `LawyerDTO`: Representa os dados de um advogado.
- `MessageResponseDTO`: Utilizado para enviar mensagens de resposta.

### Entity

As entidades representam as tabelas no banco de dados e são utilizadas pelo JPA para persistir dados. As principais entidades são:

- `Processes`: Representa um processo jurídico.
- `Lawyer`: Representa um advogado.

### Service

A camada de serviço (ProcessesService) contém a lógica de negócio e é responsável por interagir com o repositório para realizar as operações no banco de dados. As principais operações incluem:

- Criação de um novo processo.
- Busca de um processo pelo ID.
- Busca de todos os processos.
- Atualização de um processo existente.
- Deleção de um processo pelo ID.

## Configuração e Execução

### Pré-requisitos

- Java 11 ou superior
- Maven


