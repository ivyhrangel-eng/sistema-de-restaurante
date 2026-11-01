package app;

import pedido.Pedido;
import cardapio.ItemCardapio;
import cardapio.Lanche;

public class Main {
    public static void main(String[] args) {
        RestauranteFacade sistema = new RestauranteFacade();
        MenuConsole menu = new MenuConsole();
        int opcao;

        do {
            menu.exibirMenu();
            opcao = menu.lerOpcao();

            switch (opcao) {
                case 1:
                    // Registrar novo pedido
                    String nomeCliente = menu.pedirNomeCliente();
                    try {
                        Pedido pedido = sistema.criarPedido(nomeCliente);
                        menu.exibirMensagem("✅ Pedido #" + pedido.getNumero() + " criado para " + pedido.getNomeCliente());

                        boolean continuarItens;
                        do {
                            int opcaoLanche = menu.exibirCardapioEChofrer();
                            try {
                                Lanche lancheBase = sistema.criarLancheDoCardapio(opcaoLanche);
                                menu.personalizarLanche(lancheBase);

                                ItemCardapio itemFinal = lancheBase;
                                int opcaoAdicional;
                                do {
                                    opcaoAdicional = menu.exibirMenuAdicionais();
                                    if (opcaoAdicional == 1) {
                                        itemFinal = sistema.adicionarCheddar(itemFinal);
                                        menu.exibirMensagem("➕ Cheddar aplicado!");
                                    } else if (opcaoAdicional == 2) {
                                        itemFinal = sistema.adicionarBaconExtra(itemFinal);
                                        menu.exibirMensagem("➕ Bacon extra aplicado!");
                                    } else if (opcaoAdicional != 0) {
                                        menu.exibirMensagem("⚠️ Opção inválida.");
                                    }
                                } while (opcaoAdicional != 0);

                                pedido.adicionarItem(itemFinal);
                                menu.exibirMensagem("✅ Item adicionado ao pedido!");

                            } catch (IllegalArgumentException e) {
                                menu.exibirMensagem("❌ Erro: " + e.getMessage());
                            }

                            continuarItens = menu.perguntarSeDesejaContinuar();
                        } while (continuarItens);

                    } catch (IllegalArgumentException e) {
                        menu.exibirMensagem("❌ Erro ao criar pedido: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Listar pedidos do dia
                    menu.exibirListaPedidos(sistema.getPedidos());
                    break;

                case 3:
                    // Modificar um pedido existente (Nível 3.2)
                    int numPedido = menu.pedirNumeroPedido();
                    Pedido pedidoMod = sistema.buscarPedido(numPedido);

                    if (pedidoMod == null) {
                        menu.exibirMensagem("❌ Pedido #" + numPedido + " não encontrado.");
                        break;
                    }

                    int opcaoMod;
                    do {
                        menu.exibirPedido(pedidoMod);
                        opcaoMod = menu.exibirMenuModificacao();

                        switch (opcaoMod) {
                            case 1:
                                // Acrescentar item novo
                                try {
                                    int opcaoLanche = menu.exibirCardapioEChofrer();
                                    Lanche lancheBase = sistema.criarLancheDoCardapio(opcaoLanche);
                                    menu.personalizarLanche(lancheBase);

                                    ItemCardapio itemFinal = lancheBase;
                                    int opcaoAdicional;
                                    do {
                                        opcaoAdicional = menu.exibirMenuAdicionais();
                                        if (opcaoAdicional == 1) itemFinal = sistema.adicionarCheddar(itemFinal);
                                        else if (opcaoAdicional == 2) itemFinal = sistema.adicionarBaconExtra(itemFinal);
                                    } while (opcaoAdicional != 0);

                                    pedidoMod.adicionarItem(itemFinal);
                                    menu.exibirMensagem("✅ Item adicionado ao pedido #" + pedidoMod.getNumero());
                                } catch (Exception e) {
                                    menu.exibirMensagem("❌ Erro ao adicionar item: " + e.getMessage());
                                }
                                break;

                            case 2:
                                // Remover item existente
                                int indiceRemover = menu.escolherItemParaRemover(pedidoMod);
                                try {
                                    pedidoMod.removerItem(indiceRemover);
                                    menu.exibirMensagem("🗑️ Item removido com sucesso!");
                                } catch (Exception e) {
                                    menu.exibirMensagem("❌ Erro: Índice inválido ou pedido vazio.");
                                }
                                break;

                            case 3:
                                // Cancelar pedido inteiro
                                try {
                                    sistema.cancelarPedido(numPedido);
                                    menu.exibirMensagem("🚫 Pedido #" + numPedido + " cancelado e removido do sistema.");
                                    opcaoMod = 0; // Sai do sub-menu
                                } catch (Exception e) {
                                    menu.exibirMensagem("❌ Erro ao cancelar pedido.");
                                }
                                break;

                            case 0:
                                break;
                            default:
                                menu.exibirMensagem("⚠️ Opção inválida.");
                        }
                    } while (opcaoMod != 0 && sistema.buscarPedido(numPedido) != null);
                    break;

                case 4:
                    // Fechar restaurante e processar financeiro + relatórios
                    for (Pedido p : sistema.getPedidos()) {
                        System.out.println("\nProcessando pagamento do Pedido #" + p.getNumero() + " (" + p.getNomeCliente() + ")");
                        double gorjeta = menu.pedirGorjeta();
                        sistema.registrarNoCaixa(p, gorjeta);
                    }

                    double totalVendas = sistema.getTotalVendasCaixa();
                    double totalGorjetas = sistema.getTotalGorjetasCaixa();
                    var combinacoes = sistema.getCombinacoesDiferentes();
                    var bases = sistema.getBasesDiferentes();

                    menu.exibirFechamentoCompleto(totalVendas, totalGorjetas, combinacoes, bases);
                    opcao = 0; // Encerra o loop
                    break;

                default:
                    menu.exibirMensagem("⚠️ Opção inválida!");
            }

        } while (opcao != 0);
    }
}