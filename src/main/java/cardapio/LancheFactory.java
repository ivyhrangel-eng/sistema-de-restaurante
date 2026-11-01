package cardapio;

public class LancheFactory {

    // Cada método retorna uma NOVA instância de Lanche com os padrões do cardápio
    public static Lanche xBurger() {
        return new Lanche("X-Burger", 16.0);
    }

    public static Lanche xSalada() {
        return new Lanche("X-Salada", 18.0);
    }

    public static Lanche xBacon() {
        return new Lanche("X-Bacon", 22.0);
    }
}