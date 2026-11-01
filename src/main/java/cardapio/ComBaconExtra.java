package cardapio;

public class ComBaconExtra implements ItemCardapio {
    private ItemCardapio item; // O item que está a ser "embrulhado"

    public ComBaconExtra(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        // Junta a descrição anterior com o formato exato do bacon extra
        return item.getDescricao() + " + bacon extra";
    }

    @Override
    public double getPreco() {
        // Soma o preço anterior com os R$ 6,00 do bacon extra
        return item.getPreco() + 6.0;
    }
    @Override
    public String getNomeBase() {
        return item.getNomeBase(); // Delega para o item interno
    }
}