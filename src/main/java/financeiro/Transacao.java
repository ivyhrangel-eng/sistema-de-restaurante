package financeiro;

import java.util.Objects;

public class Transacao {
    private int numeroPedido;
    private double valor;
    private double gorjeta;

    public Transacao(int numeroPedido, double valor, double gorjeta) {
        this.numeroPedido = numeroPedido;
        this.valor = valor;
        this.gorjeta = gorjeta;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double getValor() {
        return valor;
    }

    public double getGorjeta() {
        return gorjeta;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return numeroPedido == transacao.numeroPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPedido);
    }
}