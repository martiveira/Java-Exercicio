public class Categoria {

    // ==========================
    // ATRIBUTOS
    // ==========================
    private int    id;
    private String nome;

    // ==========================
    // CONSTRUTOR
    // ==========================
    public Categoria(int id, String nome) {
        this.id   = id;
        this.nome = nome;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    // ==========================
    // EXIBIÇÃO
    // ==========================
    @Override
    public String toString() {
        return "[" + this.id + "] " + this.nome;
    }
}
