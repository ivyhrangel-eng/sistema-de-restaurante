package app;

import pedido.Pedido;
import cardapio.ItemCardapio;
import java.util.Collection;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MenuConsole {
    private Scanner scanner = new Scanner(System.in);


    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int numero = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer após ler o número
                return numero;
            } catch (InputMismatchException e) {
                System.out.println(" Entrada inválida! Por favor, digite apenas números inteiros.");
                scanner.nextLine(); // Descarta a entrada incorreta para evitar loop infinito
            }
        }
    }

    public void exibirMenu() {
        System.out.println("\n=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Modificar ou cancelar pedido");
        System.out.println("4 - Fechar restaurante");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        return lerInteiro("Escolha uma opção: ");
    }

    public String pedirNomeCliente() {
        System.out.print("Digite o nome do cliente: ");
        return scanner.nextLine();
    }

    public int exibirCardapioEChofrer() {
        System.out.println("\n--- CARDÁPIO ---");
        System.out.println("1 - X-Burger — R$ 16,00");
        System.out.println("2 - X-Salada — R$ 18,00");
        System.out.println("3 - X-Bacon — R$ 22,00");
        return lerInteiro("Escolha o lanche desejado: ");
    }

    public int exibirMenuAdicionais() {
        System.out.println("\n--- ADICIONAIS ---");
        System.out.println("1 - Adicionar Cheddar (+ R$ 4,00)");
        System.out.println("2 - Adicionar Bacon Extra (+ R$ 6,00)");
        System.out.println("0 - Concluir adicionais");
        return lerInteiro("Escolha um adicional (ou 0 para avançar): ");
    }

    public boolean perguntarSeDesejaContinuar() {
        System.out.print("Deseja adicionar mais um item? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s") || resposta.equals("sim");
    }

    public void exibirListaPedidos(Collection<Pedido> pedidos) {
        System.out.println("\n=== PEDIDOS DO DIA ===");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado até o momento.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println("Pedido #" + p.getNumero() + " | Cliente: " + p.getNomeCliente() + " | Total: R$ " + p.calcularTotal());
        }
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("\n--- Detalhes do Pedido #" + pedido.getNumero() + " ---");
        System.out.println("Cliente: " + pedido.getNomeCliente());
        System.out.println("Itens consumidos:");
        if (pedido.getItens().isEmpty()) {
            System.out.println("  (Nenhum item adicionado ainda)");
        } else {
            for (int i = 0; i < pedido.getItens().size(); i++) {
                ItemCardapio item = pedido.getItens().get(i);
                System.out.println("  [" + (i + 1) + "] " + item.getDescricao() + " (R$ " + item.getPreco() + ")");
            }
        }
        System.out.println("Total do pedido: R$ " + pedido.calcularTotal());
    }

    public void exibirFechamentoDoDia(double total, java.util.HashSet<String> combinacoes) {
        System.out.println("\n=== FECHAMENTO DO RESTAURANTE ===");
        System.out.println(" Faturamento Total do Dia: R$ " + total);
        System.out.println(" Total de combinações diferentes pedidas: " + combinacoes.size());
        System.out.println("Lista de combinações registradas:");
        if (combinacoes.isEmpty()) {
            System.out.println("  (Nenhuma combinação registrada)");
        } else {
            for (String desc : combinacoes) {
                System.out.println("  - " + desc);
            }
        }
        System.out.println("Restaurante fechado com sucesso.");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public int pedirNumeroPedido() {
        return lerInteiro("Digite o número do pedido: ");
    }

    public int exibirMenuModificacao() {
        System.out.println("\n--- MODIFICAR PEDIDO ---");
        System.out.println("1 - Adicionar item ao pedido");
        System.out.println("2 - Remover item existente");
        System.out.println("3 - Cancelar pedido inteiro");
        System.out.println("0 - Voltar ao menu principal");
        return lerInteiro("Escolha uma opção: ");
    }

    public int escolherItemParaRemover(Pedido pedido) {
        System.out.println("\n--- ITENS DO PEDIDO #" + pedido.getNumero() + " ---");
        var itens = pedido.getItens();
        if (itens.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + " - " + itens.get(i).getDescricao() + " (R$ " + itens.get(i).getPreco() + ")");
        }
        int escolha = lerInteiro("Digite o número do item que deseja remover: ");
        return escolha - 1; // Retorna o índice real da lista (começa em 0)
    }

    public void personalizarLanche(cardapio.Lanche lanche) {
        System.out.println("\n--- Personalizar: " + lanche.getNome() + " ---");

        System.out.print("Deseja retirar o queijo? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            lanche.setQueijo(false);
        }

        System.out.print("Deseja retirar a alface? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            lanche.setAlface(false);
        }

        System.out.print("Deseja retirar o tomate? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            lanche.setTomate(false);
        }

        System.out.print("Deseja retirar a cebola? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            lanche.setCebola(false);
        }
    }

    public double pedirGorjeta() {
        System.out.print("Deseja deixar alguma gorjeta para o entregador/atendente? (R$): ");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public void exibirFechamentoCompleto(double totalVendas, double totalGorjetas, java.util.HashSet<String> combinacoes, java.util.HashSet<String> bases) {
        System.out.println("\n=== FECHAMENTO PROFISSIONAL DO RESTAURANTE ===");
        System.out.println(" Faturamento em Vendas: R$ " + totalVendas);
        System.out.println("tip Total de Gorjetas Arrecadadas: R$ " + totalGorjetas);
        System.out.println("Faturamento Geral (Vendas + Gorjetas): R$ ( " + (totalVendas + totalGorjetas) + " )");

        System.out.println("\n Lanches base diferentes vendidos: " + bases.size());
        for (String base : bases) {
            System.out.println("  - " + base);
        }

        System.out.println("\n Combinações completas diferentes pedidas: " + combinacoes.size());
        for (String comb : combinacoes) {
            System.out.println("  - " + comb);
        }
        System.out.println("\nRestaurante fechado com sucesso. Até amanhã!");
    }
}
