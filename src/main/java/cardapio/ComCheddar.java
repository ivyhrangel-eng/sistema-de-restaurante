package cardapio;

public class ComCheddar implements ItemCardapio {
    private ItemCardapio item; // O item que está a ser "embrulhado"

    public ComCheddar(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        // Junta a descrição do item anterior com o formato exato exigido
        return item.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco() {
        // Soma o preço do item anterior com os R$ 4,00 do cheddar
        return item.getPreco() + 4.0;
    }
    @Override
    public String getNomeBase() {
        return item.getNomeBase(); // Delega para o item interno
    }
}