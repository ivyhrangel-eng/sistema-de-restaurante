package app;

import pedido.GerenciadorPedidos;
import pedido.Pedido;
import cardapio.Lanche;
import cardapio.LancheFactory; // <-- Importante para usar a fábrica
import java.util.Collection;
import cardapio.ItemCardapio;
import cardapio.ComBaconExtra;
import cardapio.ComCheddar;
public class RestauranteFacade {
    private GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public Collection<Pedido> getPedidos() {
        return gerenciador.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }

    // A Facade agora delega a criação dos lanches para a LancheFactory
    public Lanche criarLancheDoCardapio(int opcaoCardapio) {
        switch (opcaoCardapio) {
            case 1:
                return LancheFactory.xBurger();
            case 2:
                return LancheFactory.xSalada();
            case 3:
                return LancheFactory.xBacon();
            default:
                throw new IllegalArgumentException("Opção de lanche inválida no cardápio.");
        }
    }
    public ItemCardapio adicionarCheddar(ItemCardapio item) {
        return new ComCheddar(item);
    }

    public ItemCardapio adicionarBaconExtra(ItemCardapio item) {
        return new ComBaconExtra(item);
    }
    public Pedido buscarPedido(int numero) {
        return gerenciador.buscarPedido(numero);
    }

    // Expõe a exclusão/cancelamento do pedido
    public void cancelarPedido(int numero) {
        gerenciador.removerPedido(numero);
    }

    // Expõe o HashSet de combinações diferentes para o fechamento
    public java.util.HashSet<String> getCombinacoesDiferentes() {
        return gerenciador.getCombinacoesDiferentes();
    }

    public java.util.HashSet<String> getBasesDiferentes() {
        return gerenciador.getBasesDiferentes();
    }

    public void registrarNoCaixa(Pedido pedido, double gorjeta) {
        financeiro.Caixa.getInstancia().registrarTransacao(pedido, gorjeta);
    }

    public double getTotalVendasCaixa() {
        return financeiro.Caixa.getInstancia().calcularTotalVendas();
    }

    public double getTotalGorjetasCaixa() {
        return financeiro.Caixa.getInstancia().calcularTotalGorjetas();
    }
}