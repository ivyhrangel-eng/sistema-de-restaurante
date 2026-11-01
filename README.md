# Sistema de Restaurante em Java

Sistema de gerenciamento de restaurante desenvolvido em **Java** como parte de uma jornada de aprendizado em Orientação a Objetos, aplicação de princípios **SOLID**, **Design Patterns** e arquitetura limpa em camadas.



# Funcionalidades do Sistema

O sistema é operado via console (linha de comando) e conta com um menu interativo robusto que permite:
* **Gestão de Pedidos:** Abertura de comandas com atribuição automática de números, adição de itens, listagem e cancelamento/modificação.
* **Cardápio e Personalização:** Escolha de lanches base (*X-Burger*, *X-Salada*, *X-Bacon*) com a possibilidade de retirar ingredientes (sem alteração de preço).
* **Adicionais Dinâmicos (Decorator):** Acréscimo de opcionais pagos (*Cheddar* e *Bacon Extra*) que atualizam descrições e valores de forma flexível.
* **Módulo Financeiro (Caixa & Singleton):** Fechamento de caixa unificado, controle de transações sem duplicidade e registro de gorjetas.
* **Relatórios de Inteligência (HashSet):** Exibição de estatísticas no fechamento do dia, incluindo faturamento total, total de gorjetas, total de lanches base diferentes e combinações completas únicas vendidas.
* **Blindagem de Entrada (Robustez):** Tratamento completo de exceções (como `InputMismatchException`) para impedir que entradas incorretas quebrem o sistema.



# Arquitetura e Padrões de Projeto Utilizados

O projeto está estruturado em pacotes seguindo a separação de responsabilidades:
* **`app`**: Camada de apresentação contendo o `Main`, o `MenuConsole` (exclusivo para I/O) e o `RestauranteFacade` (ponto de entrada unificado para as regras de negócio).
* **`cardapio`**: Contém a interface `ItemCardapio`, a classe `Lanche`, a **`LancheFactory`** (para criação centralizada) e os **`Decorators`** (`ComCheddar`, `ComBaconExtra`).
* **`pedido`**: Gerencia as comandas através de `Pedido` e o `GerenciadorPedidos` utilizando `HashMap` e `HashSet`.
* **`financeiro`**: Contém a classe `Transacao` (`equals` e `hashcode` por ID de pedido) e o **`Caixa`** implementado sob o padrão **Singleton**.


# Testes Unitários

O projeto possui suítes de testes automatizados implementadas com **JUnit** em `src/test/java`, cobrindo:
* Validação contra preços negativos.
* Funcionamento correto do padrão *Decorator* (soma de valores).
* Prevenção de transações duplicadas no caixa via `HashSet`.
* Delegação correta do nome base pelos lanches embrulhados.
* Cálculo correto de gorjetas.



# Como Executar o Projeto

1. Certifique-se de ter o **JDK (Java Development Kit)** instalado em sua máquina.
2. Clone este repositório ou baixe o código-fonte.
3. Abra o projeto em sua IDE favorita (como **IntelliJ IDEA** ou Eclipse).
4. Navegue até a classe principal `Main.java` (localizada no pacote `app`).
5. Execute a classe para iniciar o menu interativo no terminal.



