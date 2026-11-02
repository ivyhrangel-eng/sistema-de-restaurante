package financeiro;

import java.util.HashSet;
import pedido.Pedido;

public class Caixa {
    private static Caixa instanciaUnica; // Garante que só existe um Caixa no sistema
    private HashSet<Transacao> transacoes = new HashSet<>();

    private Caixa() {

    }

    public static synchronized Caixa getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Caixa();
        }
        return instanciaUnica;
    }


    public boolean registrarTransacao(Pedido pedido, double gorjeta) {
        Transacao t = new Transacao(pedido.getNumero(), pedido.calcularTotal(), gorjeta);
        return transacoes.add(t);
    }

    public double calcularTotalVendas() {
        double total = 0.0;
        for (Transacao t : transacoes) {
            total += t.getValor();
        }
        return total;
    }

    public double calcularTotalGorjetas() {
        double total = 0.0;
        for (Transacao t : transacoes) {
            total += t.getGorjeta();
        }
        return total;
    }

    public HashSet<Transacao> getTransacoes() {
        return transacoes;
    }

    public void limparParaTestes() {
        this.transacoes.clear();
    }
}