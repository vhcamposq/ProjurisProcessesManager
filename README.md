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
### Instalação

Clone o repositório:

```bash
git clone https://github.com/vhcamposq/ProjurisProcessesManager.git
cd ProjurisProcessesManager
```

Para iniciar a aplicação, execute o comando:
```bash
mvn spring-boot:run
```

Testes:

Para executar os testes, utilize o comando:

```bash
mvn test
```
A aplicação estará disponível em http://localhost:8080.

### Dependências

As dependências utilizadas no projeto são:

- **spring-boot-devtools**: Facilita o desenvolvimento com funcionalidades como reinicialização automática e live reload.
- **spring-boot-starter-web**: Utilizado para construir aplicações web, incluindo RESTful, usando Spring MVC.
- **spring-boot-starter-data-jpa**: Facilita a integração com bancos de dados utilizando JPA (Java Persistence API).
- **spring-boot-starter-actuator**: Adiciona funcionalidades de monitoramento e gerenciamento à aplicação.
- **h2database**: Banco de dados em memória utilizado para testes e desenvolvimento.
- **lombok**: Biblioteca que reduz o código boilerplate com anotações para gerar getters, setters, construtores e outros métodos.
- **junit-jupiter-engine**: Framework de testes utilizado para escrever e executar testes unitários.
- **mockito**: Framework de testes utilizado para criar mocks e realizar testes unitários.
- **mapstruct**: Mapeador de código utilizado para converter entre objetos de diferentes tipos.
- **javax.validation**: Biblioteca para validar objetos de modelo utilizando anotações.
- 

As dependências em formato XML para inclusão no `pom.xml` são:


```bash
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>javax.validation</groupId>
			<artifactId>validation-api</artifactId>
			<version>2.0.1.Final</version>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>1.4.2.Final</version>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct-processor</artifactId>
			<version>1.4.2.Final</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>5.10.3</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>3.9.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-junit-jupiter</artifactId>
			<version>3.9.0</version>
			<scope>test</scope>
		</dependency>

	</dependencies>
```

## Configuração e Execução

### Criação de Processo

- POST  /api/v1/processes

```bash
{
    "name": "Processo tal",
    "caseNumber": "123456789",
    "status": "Em andamento",
    "lawyer": {
        "name": "Machado de Assis",
        "experienceYears": 30,
        "oabNumber": "123456"
    }
}


```
Busca de Processo pelo ID

- GET  /api/v1/processes/{id}
Busca de Todos os Processos

- GET  /api/v1/processes
Atualização de Processo

- PUT  /api/v1/processes/{id}
```bash
{
  "name": "Nome do Processo Atualizado",
  "caseNumber": "Número do Caso Atualizado",
  "status": "Status Atualizado",
  "lawyer": {
    "id": 1,
    "name": "Nome do Advogado Atualizado",
    "oabNumber": "Número OAB Atualizado"
  }
}
```
Deleção de Processo
- DELETE  /api/v1/processes/{id}

##
