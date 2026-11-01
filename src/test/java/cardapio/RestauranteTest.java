package cardapio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pedido.Pedido;
import financeiro.Caixa;

public class RestauranteTest {

    @Test
    void naoDevePermitirPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lanche("X-Invalido", -5.0);
        });
    }

    @Test
    void xBaconComCheddarCusta26() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());
        assertEquals(26.0, lanche.getPreco(), 0.001);
    }

    @Test
    void transacoesIguaisNaoDevemDuplicarNoCaixa() {
        Caixa caixa = Caixa.getInstancia();
        Pedido p = new Pedido(500, "Cliente Teste");

        boolean primeiraVez = caixa.registrarTransacao(p, 5.0);
        boolean segundaVez = caixa.registrarTransacao(p, 5.0); // Mesmo número de pedido

        assertTrue(primeiraVez);
        assertFalse(segundaVez, "O HashSet do caixa deve bloquear transações duplicadas com base no ID do pedido");
    }

    @Test
    void nomeBaseDeveSerPreservadoPeloDecorator() {
        ItemCardapio lanche = LancheFactory.xBacon();
        ItemCardapio lancheEnfeitado = new ComBaconExtra(new ComCheddar(lanche));

        assertEquals("X-Bacon", lancheEnfeitado.getNomeBase(), "O decorator deve delegar corretamente o nome do lanche base");
    }

    @Test
    void calculoDeGorjetasNoCaixaDeveSomarCorretamente() {
        Caixa caixa = Caixa.getInstancia();
        Pedido p1 = new Pedido(777, "Ana");

        caixa.registrarTransacao(p1, 15.50);

        assertEquals(15.50, caixa.calcularTotalGorjetas(), 0.001);
    }
}