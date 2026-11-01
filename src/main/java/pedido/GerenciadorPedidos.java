package pedido;

import cardapio.ItemCardapio;

import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;

public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente) {
        Pedido novo = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novo.getNumero(), novo);
        contadorPedidos++;
        return novo;
    }

    public Pedido buscarPedido(int numero) {
        return pedidos.get(numero); // Devolve o Pedido ou null se não existir
    }

    public Collection<Pedido> getPedidos() {
        return pedidos.values(); // Devolve a coleção para quem for exibir
    }

    public double calcularTotalDoDia() {
        double totalDia = 0.0;
        for (Pedido p : pedidos.values()) {
            totalDia += p.calcularTotal();
        }
        return totalDia;
    }


    public void removerPedido(int numero) {
        if (pedidos.containsKey(numero)) {
            pedidos.remove(numero);
        } else {
            throw new IllegalArgumentException("Pedido #" + numero + " não encontrado para cancelamento.");
        }
    }


    public HashSet<String> getCombinacoesDiferentes() {
        HashSet<String> combinacoes = new HashSet<>();
        for (Pedido p : pedidos.values()) {
            for (ItemCardapio item : p.getItens()) {
                combinacoes.add(item.getDescricao()); // O HashSet rejeita duplicadas automaticamente
            }
        }
        return combinacoes;
    }

    public HashSet<String> getBasesDiferentes() {
        HashSet<String> bases = new HashSet<>();
        for (Pedido p : pedidos.values()) {
            for (ItemCardapio item : p.getItens()) {
                bases.add(item.getNomeBase());
            }
        }
        return bases;
    }
}
