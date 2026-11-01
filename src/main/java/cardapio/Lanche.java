package cardapio;

public class Lanche implements ItemCardapio {
    private String nome;
    private double preco;

    // Ingredientes vêm true por padrão (o lanche nasce completo)
    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
    private boolean cebola = true;

    public Lanche(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do lanche não pode ser vazio.");
        }
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    // Getters e Setters para os ingredientes personalizáveis
    public boolean isQueijo() { return queijo; }
    public void setQueijo(boolean queijo) { this.queijo = queijo; }

    public boolean isAlface() { return alface; }
    public void setAlface(boolean alface) { this.alface = alface; }

    public boolean isTomate() { return tomate; }
    public void setTomate(boolean tomate) { this.tomate = tomate; }

    public boolean isCebola() { return cebola; }
    public void setCebola(boolean cebola) { this.cebola = cebola; }

    // Descrição dinâmica que acrescenta os ingredientes removidos
    @Override
    public String getDescricao() {
        String desc = nome;
        if (!queijo) desc += " sem queijo";
        if (!alface) desc += " sem alface";
        if (!tomate) desc += " sem tomate";
        if (!cebola) desc += " sem cebola";
        return desc;
    }
    // Implementação do lanche base
    @Override
    public String getNomeBase() {
        return this.nome;
    }

}
