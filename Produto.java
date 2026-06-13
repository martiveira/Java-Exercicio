public class Produto {

    // ==========================
    // ATRIBUTOS
    // ==========================
    private int       id;
    private String    nome;
    private double    preco;
    private int       quantidade;
    private Categoria categoria;

    // ==========================
    // CONSTRUTOR
    // ==========================
    public Produto(int id, String nome, double preco, int quantidade, Categoria categoria) {
        this.id         = id;
        this.nome       = nome;
        this.preco      = preco;
        this.quantidade = quantidade;
        this.categoria  = categoria;
    }

    // ==========================
    // GETTERS E SETTERS
    // ==========================
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // ==========================
    // EXIBIÇÃO
    // ==========================
    @Override
    public String toString() {
        return "[" + this.id + "] " + this.nome
                + " | R$ " + String.format("%.2f", this.preco)
                + " | Qtd: " + this.quantidade
                + " | Categoria: " + this.categoria.getNome();
    }
}

