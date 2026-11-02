#  Sistema de Restaurante em Java

Um sistema de gerenciamento de restaurante desenvolvido em **Java**, com execução pelo terminal, aplicação de **Programação Orientada a Objetos (POO)**, princípios **SOLID** e padrões de projeto.

O projeto simula operações comuns de um restaurante, desde a criação e personalização de pedidos até o fechamento do caixa e a geração de estatísticas do dia.

> Projeto desenvolvido como prática de programação Java, organização de código e aplicação de conceitos de arquitetura e design de software.

---

##  Visão geral

O sistema oferece um menu interativo pelo terminal para que o usuário possa:

- Criar pedidos e associá-los a clientes.
- Escolher lanches disponíveis no cardápio.
- Personalizar ingredientes dos lanches.
- Adicionar complementos e atualizar os preços.
- Consultar, modificar e cancelar pedidos.
- Registrar pagamentos e gorjetas.
- Visualizar informações financeiras e estatísticas no fechamento do restaurante.

Os dados são gerenciados em memória durante a execução do programa.

##  Funcionalidades

###  Gerenciamento de pedidos
- Criação de pedidos com numeração automática.
- Associação de pedidos aos nomes dos clientes.
- Consulta dos pedidos registrados.
- Inclusão e remoção de itens.
- Cancelamento de pedidos.

###  Cardápio e personalização
O sistema disponibiliza três opções de lanches:

| Lanche | Preço |
|---|---:|
| X-Burger | R$ 16,00 |
| X-Salada | R$ 18,00 |
| X-Bacon | R$ 22,00 |

Também é possível personalizar os lanches, retirando ingredientes como queijo, alface, tomate e cebola.

###  Adicionais
O cliente pode acrescentar complementos ao lanche:

- Cheddar: R$ 4,00.
- Bacon extra: R$ 6,00.

Os adicionais são aplicados dinamicamente por meio do padrão **Decorator**, permitindo combinar complementos sem alterar diretamente a classe do lanche original.

###  Caixa e fechamento
- Registro de transações.
- Controle de valores das vendas.
- Registro e cálculo de gorjetas.
- Prevenção de transações duplicadas.
- Cálculo do faturamento geral.

###  Relatórios e estatísticas
No fechamento do restaurante, o sistema apresenta:

- Total de vendas.
- Total de gorjetas.
- Faturamento geral.
- Quantidade de lanches base diferentes vendidos.
- Combinações completas diferentes solicitadas.

--

##  Arquitetura e organização do projeto

O código foi dividido em pacotes para separar responsabilidades e facilitar a manutenção.

```text
sistema-de-restaurante/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── app/
│   │       │   ├── Main.java
│   │       │   ├── MenuConsole.java
│   │       │   └── RestauranteFacade.java
│   │       │
│   │       ├── cardapio/
│   │       │   ├── ItemCardapio.java
│   │       │   ├── Lanche.java
│   │       │   ├── LancheFactory.java
│   │       │   ├── ComCheddar.java
│   │       │   └── ComBaconExtra.java
│   │       │
│   │       ├── pedido/
│   │       │   ├── Pedido.java
│   │       │   └── GerenciadorPedidos.java
│   │       │
│   │       └── financeiro/
│   │           ├── Caixa.java
│   │           └── Transacao.java
│   │
│   └── test/
│       └── java/
│           └── cardapio/
│               └── RestauranteTest.java
│
└── pom.xml
```

### Responsabilidade dos pacotes

| Pacote | Responsabilidade |
|---|---|
| `app` | Inicialização, interação com o usuário e coordenação das funcionalidades. |
| `cardapio` | Representação dos lanches, criação e aplicação de adicionais. |
| `pedido` | Criação, consulta, gerenciamento e remoção de pedidos. |
| `financeiro` | Registro de transações, vendas e gorjetas. |

---

##  Conceitos e padrões de projeto aplicados

O desenvolvimento utiliza conceitos de orientação a objetos e padrões de projeto para organizar as responsabilidades do sistema.

| Conceito / Padrão | Aplicação no projeto |
|---|---|
| **POO** | Classes, objetos, encapsulamento e abstração das entidades do restaurante. |
| **SOLID** | Separação de responsabilidades e organização das classes. |
| **Factory** | Centralização da criação dos lanches disponíveis no cardápio. |
| **Decorator** | Adição dinâmica de cheddar e bacon extra aos lanches. |
| **Facade** | Centralização do acesso às operações do sistema por meio de `RestauranteFacade`. |
| **Singleton** | Garantia de uma instância compartilhada da classe `Caixa`. |
| **HashMap** | Armazenamento e consulta de pedidos por número. |
| **HashSet** | Controle de transações únicas e identificação de combinações diferentes. |

---

##  Testes unitários

O projeto possui testes automatizados utilizando **JUnit 5**, localizados em `src/test/java`.

Os testes implementados verificam:

- Bloqueio de preços negativos.
- Cálculo correto do preço de um X-Bacon com cheddar.
- Prevenção de transações duplicadas no caixa.
- Preservação do nome base do lanche após a aplicação de Decorators.
- Cálculo correto das gorjetas.

Esses testes ajudam a verificar comportamentos específicos das classes e regras implementadas.

---

##  Tecnologias utilizadas

- **Java 21** — linguagem de programação.
- **Maven** — gerenciamento do projeto e dependências.
- **JUnit 5** — testes unitários.
- **Mockito** — dependência de suporte a testes.
- **IntelliJ IDEA** — ambiente de desenvolvimento.
- **Git e GitHub** — versionamento e publicação do código.

---

##  Como executar o projeto

### Pré-requisitos

Antes de executar, certifique-se de ter instalado:

- JDK 21.
- Maven.
- IntelliJ IDEA ou outra IDE compatível com Java.

### 1. Clone o repositório

```bash
git clone https://github.com/ivyhrangel-eng/sistema-de-restaurante.git
```

### 2. Acesse a pasta do projeto

```bash
cd sistema-de-restaurante
```

### 3. Abra o projeto na IDE

Importe o projeto como um projeto Maven e configure o JDK 21.

### 4. Execute a aplicação

Localize a classe:

```text
src/main/java/app/Main.java
```

Execute o método `main` para iniciar o menu interativo no terminal.

### 5. Execute os testes

Pelo terminal, na pasta do projeto:

```bash
mvn test
```

---

##  Objetivo de aprendizado

Este projeto faz parte da minha evolução prática no desenvolvimento Java.

Durante sua construção, trabalhei com organização de classes e pacotes, gerenciamento de coleções, tratamento de regras de negócio, princípios SOLID, padrões de projeto e testes unitários.

Mais do que implementar funcionalidades, o objetivo foi compreender como estruturar uma aplicação de forma organizada, reutilizável e com responsabilidades bem definidas.

---

##  Desenvolvido por

**Ivyh Rangel**

[GitHub](https://github.com/ivyhrangel-eng)
