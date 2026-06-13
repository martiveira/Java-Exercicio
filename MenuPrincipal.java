import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {

    // ==========================
    // DEPENDÊNCIAS E COLEÇÕES
    // ==========================
    static Scanner          sc           = new Scanner(System.in);
    static ArrayList<Produto>   produtos     = new ArrayList<>();
    static ArrayList<Categoria> categorias   = new ArrayList<>();

    static int proximoIdProduto   = 1;
    static int proximoIdCategoria = 1;

    static final int ESTOQUE_BAIXO = 5;

    // ==========================
    // MAIN
    // ==========================
    public static void main(String[] args) {
        menuPrincipal();
        sc.close();
    }

    // ==========================
    // MENU PRINCIPAL
    // ==========================
    public static void menuPrincipal() {

        int opcao;

        do {
            limparTela();

            System.out.println("=================================");
            System.out.println("       SISTEMA DE ESTOQUE");
            System.out.println("=================================");
            System.out.println("1. Gerenciar Produtos");
            System.out.println("2. Gerenciar Categorias");
            System.out.println("3. Relatórios");
            System.out.println("0. Sair");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiroPositivo();

            switch (opcao) {
                case 1:  menuProduto();    break;
                case 2:  menuCategoria();  break;
                case 3:  menuRelatorio();  break;
                case 0:
                    limparTela();
                    System.out.println("Sistema encerrado. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    pressionarEnter();
            }

        } while (opcao != 0);
    }

    // ==========================
    // MENU PRODUTO
    // ==========================
    public static void menuProduto() {

        int opcao;

        do {
            limparTela();

            System.out.println("=================================");
            System.out.println("          MENU PRODUTO");
            System.out.println("=================================");
            System.out.println("1. Inserir Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Alterar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("0. Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiroPositivo();

            switch (opcao) {
                case 1:  cadastrarProduto();  break;
                case 2:  listarProdutos();    break;
                case 3:  alterarProduto();    break;
                case 4:  excluirProduto();    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pressionarEnter();
            }

        } while (opcao != 0);
    }

    // ==========================
    // MENU CATEGORIA
    // ==========================
    public static void menuCategoria() {

        int opcao;

        do {
            limparTela();

            System.out.println("=================================");
            System.out.println("         MENU CATEGORIA");
            System.out.println("=================================");
            System.out.println("1. Inserir Categoria");
            System.out.println("2. Listar Categorias");
            System.out.println("3. Alterar Categoria");
            System.out.println("4. Excluir Categoria");
            System.out.println("0. Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiroPositivo();

            switch (opcao) {
                case 1:  cadastrarCategoria();  break;
                case 2:  listarCategorias();    break;
                case 3:  alterarCategoria();    break;
                case 4:  excluirCategoria();    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pressionarEnter();
            }

        } while (opcao != 0);
    }

    // ==========================
    // MENU RELATÓRIOS
    // ==========================
    public static void menuRelatorio() {

        int opcao;

        do {
            limparTela();

            System.out.println("=================================");
            System.out.println("            RELATÓRIOS");
            System.out.println("=================================");
            System.out.println("1. Relatório de Estoque");
            System.out.println("2. Relatório por Categoria");
            System.out.println("0. Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiroPositivo();

            switch (opcao) {
                case 1:  relatorioEstoque();    break;
                case 2:  relatorioCategoria();  break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pressionarEnter();
            }

        } while (opcao != 0);
    }

    // ==========================
    // CRUD PRODUTO
    // ==========================

    public static void cadastrarProduto() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         INSERIR PRODUTO");
        System.out.println("=================================");

        if (categorias.isEmpty()) {
            System.out.println("ERRO: Cadastre ao menos uma categoria antes de inserir produtos.");
            pressionarEnter();
            return;
        }

        sc.nextLine(); // limpa buffer

        System.out.print("Nome do produto: ");
        String nome = sc.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("ERRO: Nome não pode ser vazio.");
            pressionarEnter();
            return;
        }

        System.out.print("Preço (R$): ");
        double preco = lerDecimalPositivo();

        if (preco < 0) {
            System.out.println("ERRO: Preço não pode ser negativo.");
            pressionarEnter();
            return;
        }

        System.out.print("Quantidade em estoque: ");
        int quantidade = lerInteiroPositivo();

        // Exibe categorias disponíveis para escolha
        System.out.println("\nCategorias disponíveis:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + categorias.get(i).getNome());
        }
        System.out.print("Escolha o número da categoria: ");
        int numCategoria = lerInteiroPositivo();

        if (numCategoria < 1 || numCategoria > categorias.size()) {
            System.out.println("ERRO: Categoria inválida.");
            pressionarEnter();
            return;
        }

        // Recupera o objeto Categoria selecionado
        Categoria categoriaSelecionada = categorias.get(numCategoria - 1);

        // Cria o objeto Produto com todos os dados
        Produto novoProduto = new Produto(
                proximoIdProduto++,
                nome,
                preco,
                quantidade,
                categoriaSelecionada
        );

        produtos.add(novoProduto);

        System.out.println("\nProduto cadastrado com sucesso!");
        System.out.println(novoProduto);
        pressionarEnter();
    }

    public static void listarProdutos() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         LISTA DE PRODUTOS");
        System.out.println("=================================");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        boolean temEstoqueBaixo = false;

        System.out.printf("%-5s %-20s %-12s %-8s %-15s%n",
                "ID", "Nome", "Preço", "Qtd", "Categoria");
        System.out.println("-".repeat(63));

        for (Produto p : produtos) {
            String alerta = p.getQuantidade() <= ESTOQUE_BAIXO ? " ⚠" : "";
            System.out.printf("%-5d %-20s R$ %-9.2f %-8d %-15s%s%n",
                    p.getId(),
                    p.getNome(),
                    p.getPreco(),
                    p.getQuantidade(),
                    p.getCategoria().getNome(),
                    alerta);

            if (p.getQuantidade() <= ESTOQUE_BAIXO) {
                temEstoqueBaixo = true;
            }
        }

        System.out.println("-".repeat(63));
        System.out.println("Total de produtos: " + produtos.size());

        if (temEstoqueBaixo) {
            System.out.println("\n⚠  ALERTA: Há produtos com estoque baixo (≤ " + ESTOQUE_BAIXO + " unidades)!");
        }

        pressionarEnter();
    }

    public static void alterarProduto() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         ALTERAR PRODUTO");
        System.out.println("=================================");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        exibirProdutosResumido();

        System.out.print("ID do produto a alterar: ");
        int id = lerInteiroPositivo();

        Produto produto = buscarProdutoPorId(id);

        if (produto == null) {
            System.out.println("ERRO: Produto com ID " + id + " não encontrado.");
            pressionarEnter();
            return;
        }

        System.out.println("\nProduto selecionado: " + produto.getNome());
        System.out.printf("Preço atual: R$ %.2f%n", produto.getPreco());
        System.out.print("Novo preço (R$): ");
        double novoPreco = lerDecimalPositivo();

        if (novoPreco < 0) {
            System.out.println("ERRO: Preço não pode ser negativo.");
            pressionarEnter();
            return;
        }

        // Atualiza o atributo do objeto via setter
        produto.setPreco(novoPreco);

        System.out.println("\nProduto alterado com sucesso!");
        System.out.println(produto);
        pressionarEnter();
    }

    public static void excluirProduto() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         EXCLUIR PRODUTO");
        System.out.println("=================================");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        exibirProdutosResumido();

        System.out.print("ID do produto a excluir: ");
        int id = lerInteiroPositivo();

        Produto produto = buscarProdutoPorId(id);

        if (produto == null) {
            System.out.println("ERRO: Produto com ID " + id + " não encontrado.");
            pressionarEnter();
            return;
        }

        System.out.println("\nProduto selecionado: " + produto.getNome());
        System.out.print("Confirma a exclusão? (S/N): ");
        sc.nextLine(); // limpa buffer
        String confirmacao = sc.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            produtos.remove(produto); // remove o objeto da lista
            System.out.println("\nProduto excluído com sucesso!");
        } else {
            System.out.println("\nExclusão cancelada.");
        }

        pressionarEnter();
    }

    // ==========================
    // CRUD CATEGORIA
    // ==========================

    public static void cadastrarCategoria() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         INSERIR CATEGORIA");
        System.out.println("=================================");

        sc.nextLine(); // limpa buffer

        System.out.print("Nome da categoria: ");
        String nome = sc.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("ERRO: Nome não pode ser vazio.");
            pressionarEnter();
            return;
        }

        // Cria o objeto Categoria com ID auto-incrementado
        Categoria novaCategoria = new Categoria(proximoIdCategoria++, nome);
        categorias.add(novaCategoria);

        System.out.println("\nCategoria cadastrada com sucesso!");
        System.out.println(novaCategoria);
        pressionarEnter();
    }

    public static void listarCategorias() {

        limparTela();

        System.out.println("=================================");
        System.out.println("        LISTA DE CATEGORIAS");
        System.out.println("=================================");

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        System.out.printf("%-5s %-25s %-15s%n", "ID", "Nome", "Qtd. Produtos");
        System.out.println("-".repeat(47));

        for (Categoria c : categorias) {
            int qtd = contarProdutosDaCategoria(c);
            System.out.printf("%-5d %-25s %-15d%n",
                    c.getId(), c.getNome(), qtd);
        }

        System.out.println("-".repeat(47));
        System.out.println("Total de categorias: " + categorias.size());
        pressionarEnter();
    }

    public static void alterarCategoria() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         ALTERAR CATEGORIA");
        System.out.println("=================================");

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        exibirCategoriasResumido();

        System.out.print("ID da categoria a alterar: ");
        int id = lerInteiroPositivo();

        Categoria categoria = buscarCategoriaPorId(id);

        if (categoria == null) {
            System.out.println("ERRO: Categoria com ID " + id + " não encontrada.");
            pressionarEnter();
            return;
        }

        System.out.println("\nCategoria selecionada: " + categoria.getNome());
        sc.nextLine(); // limpa buffer
        System.out.print("Novo nome: ");
        String novoNome = sc.nextLine().trim();

        if (novoNome.isEmpty()) {
            System.out.println("ERRO: Nome não pode ser vazio.");
            pressionarEnter();
            return;
        }

        // Atualiza o atributo do objeto via setter
        categoria.setNome(novoNome);

        System.out.println("\nCategoria alterada com sucesso!");
        System.out.println(categoria);
        pressionarEnter();
    }

    public static void excluirCategoria() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         EXCLUIR CATEGORIA");
        System.out.println("=================================");

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        exibirCategoriasResumido();

        System.out.print("ID da categoria a excluir: ");
        int id = lerInteiroPositivo();

        Categoria categoria = buscarCategoriaPorId(id);

        if (categoria == null) {
            System.out.println("ERRO: Categoria com ID " + id + " não encontrada.");
            pressionarEnter();
            return;
        }

        // Bloqueia exclusão se houver produtos vinculados
        int qtdVinculados = contarProdutosDaCategoria(categoria);
        if (qtdVinculados > 0) {
            System.out.println("\nERRO: Não é possível excluir a categoria \""
                    + categoria.getNome() + "\".");
            System.out.println("Ela possui " + qtdVinculados + " produto(s) vinculado(s).");
            System.out.println("Remova ou realoque os produtos antes de excluir.");
            pressionarEnter();
            return;
        }

        System.out.println("\nCategoria selecionada: " + categoria.getNome());
        System.out.print("Confirma a exclusão? (S/N): ");
        sc.nextLine(); // limpa buffer
        String confirmacao = sc.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            categorias.remove(categoria); // remove o objeto da lista
            System.out.println("\nCategoria excluída com sucesso!");
        } else {
            System.out.println("\nExclusão cancelada.");
        }

        pressionarEnter();
    }

    // ==========================
    // RELATÓRIOS
    // ==========================

    public static void relatorioEstoque() {

        limparTela();

        System.out.println("=================================");
        System.out.println("        RELATÓRIO DE ESTOQUE");
        System.out.println("=================================");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        double valorTotal    = 0;
        int    totalBaixo    = 0;

        for (Produto p : produtos) {
            valorTotal += p.getPreco() * p.getQuantidade();
            if (p.getQuantidade() <= ESTOQUE_BAIXO) {
                totalBaixo++;
            }
        }

        System.out.println("Total de produtos       : " + produtos.size());
        System.out.printf( "Valor total em estoque  : R$ %.2f%n", valorTotal);
        System.out.println("Produtos c/ estoque baixo: " + totalBaixo
                + " (≤ " + ESTOQUE_BAIXO + " unidades)");

        // Valor detalhado por categoria
        System.out.println("\n--- Valor por Categoria ---");
        System.out.printf("%-5s %-20s %-10s %-15s%n", "ID", "Categoria", "Produtos", "Valor Total");
        System.out.println("-".repeat(53));

        for (Categoria c : categorias) {
            double valorCat = 0;
            int    qtdCat   = 0;
            for (Produto p : produtos) {
                if (p.getCategoria().getId() == c.getId()) {
                    valorCat += p.getPreco() * p.getQuantidade();
                    qtdCat++;
                }
            }
            System.out.printf("%-5d %-20s %-10d R$ %.2f%n",
                    c.getId(), c.getNome(), qtdCat, valorCat);
        }

        System.out.println("-".repeat(53));

        // Lista produtos com estoque baixo
        if (totalBaixo > 0) {
            System.out.println("\n⚠  Produtos com estoque baixo:");
            for (Produto p : produtos) {
                if (p.getQuantidade() <= ESTOQUE_BAIXO) {
                    System.out.println("   - " + p.getNome()
                            + " | Qtd: " + p.getQuantidade()
                            + " | Categoria: " + p.getCategoria().getNome());
                }
            }
        }

        pressionarEnter();
    }

    public static void relatorioCategoria() {

        limparTela();

        System.out.println("=================================");
        System.out.println("      RELATÓRIO POR CATEGORIA");
        System.out.println("=================================");

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        exibirCategoriasResumido();

        System.out.print("ID da categoria: ");
        int id = lerInteiroPositivo();

        Categoria categoria = buscarCategoriaPorId(id);

        if (categoria == null) {
            System.out.println("ERRO: Categoria com ID " + id + " não encontrada.");
            pressionarEnter();
            return;
        }

        limparTela();

        System.out.println("=================================");
        System.out.println("Categoria: " + categoria.getNome());
        System.out.println("=================================");
        System.out.printf("%-5s %-20s %-12s %-8s %-12s%n",
                "ID", "Produto", "Preço Unit.", "Qtd", "Subtotal");
        System.out.println("-".repeat(60));

        double valorTotal = 0;
        int    qtdItens   = 0;

        for (Produto p : produtos) {
            if (p.getCategoria().getId() == categoria.getId()) {
                double subtotal = p.getPreco() * p.getQuantidade();
                valorTotal += subtotal;
                qtdItens++;
                System.out.printf("%-5d %-20s R$ %-9.2f %-8d R$ %.2f%n",
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getQuantidade(),
                        subtotal);
            }
        }

        System.out.println("-".repeat(60));
        System.out.println("Total de itens    : " + qtdItens);
        System.out.printf( "Valor da categoria: R$ %.2f%n", valorTotal);

        pressionarEnter();
    }

    // ==========================
    // MÉTODOS DE BUSCA
    // ==========================

    public static Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static Categoria buscarCategoriaPorId(int id) {
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public static int contarProdutosDaCategoria(Categoria categoria) {
        int count = 0;
        for (Produto p : produtos) {
            if (p.getCategoria().getId() == categoria.getId()) {
                count++;
            }
        }
        return count;
    }

    // ==========================
    // EXIBIÇÕES AUXILIARES
    // ==========================

    public static void exibirProdutosResumido() {
        System.out.printf("%-5s %-20s %-12s%n", "ID", "Nome", "Preço");
        System.out.println("-".repeat(40));
        for (Produto p : produtos) {
            System.out.printf("%-5d %-20s R$ %.2f%n",
                    p.getId(), p.getNome(), p.getPreco());
        }
        System.out.println("-".repeat(40));
    }

    public static void exibirCategoriasResumido() {
        System.out.printf("%-5s %-25s%n", "ID", "Categoria");
        System.out.println("-".repeat(32));
        for (Categoria c : categorias) {
            System.out.printf("%-5d %-25s%n", c.getId(), c.getNome());
        }
        System.out.println("-".repeat(32));
    }

    // ==========================
    // UTILITÁRIOS DE ENTRADA
    // ==========================

    public static int lerInteiroPositivo() {
        while (true) {
            try {
                int valor = sc.nextInt();
                if (valor < 0) {
                    System.out.print("Valor não pode ser negativo. Tente novamente: ");
                } else {
                    return valor;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }

    public static double lerDecimalPositivo() {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Entrada inválida. Digite um valor numérico: ");
            }
        }
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pressionarEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }
}