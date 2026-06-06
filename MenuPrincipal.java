import java.util.Scanner;

public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);

    // ==========================
    // ESTRUTURA DE DADOS
    // ==========================

    // Categorias
    static String[] nomeCategorias   = new String[20];
    static int      totalCategorias  = 0;

    // Produtos
    static String[] nomeProdutos     = new String[100];
    static double[] precoProdutos    = new double[100];
    static int[]    quantProdutos    = new int[100];
    static int[]    categoriaProduto = new int[100]; // índice da categoria
    static int      totalProdutos    = 0;

    static final int ESTOQUE_BAIXO   = 5; // limite para alerta

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
                case 2:  listarProduto();     break;
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
                case 2:  listarCategoria();     break;
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
            System.out.println("           RELATÓRIOS");
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
        System.out.println("        INSERIR PRODUTO");
        System.out.println("=================================");

        if (totalCategorias == 0) {
            System.out.println("ERRO: Cadastre ao menos uma categoria antes de inserir produtos.");
            pressionarEnter();
            return;
        }

        if (totalProdutos >= nomeProdutos.length) {
            System.out.println("ERRO: Limite máximo de produtos atingido.");
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

        if (quantidade < 0) {
            System.out.println("ERRO: Quantidade não pode ser negativa.");
            pressionarEnter();
            return;
        }

        System.out.println("\nCategorias disponíveis:");
        for (int i = 0; i < totalCategorias; i++) {
            System.out.println("  " + (i + 1) + ". " + nomeCategorias[i]);
        }
        System.out.print("Escolha o número da categoria: ");
        int numCategoria = lerInteiroPositivo();

        if (numCategoria < 1 || numCategoria > totalCategorias) {
            System.out.println("ERRO: Categoria inválida.");
            pressionarEnter();
            return;
        }

        nomeProdutos[totalProdutos]     = nome;
        precoProdutos[totalProdutos]    = preco;
        quantProdutos[totalProdutos]    = quantidade;
        categoriaProduto[totalProdutos] = numCategoria - 1; // índice 0-based
        totalProdutos++;

        System.out.println("\nProduto cadastrado com sucesso!");
        pressionarEnter();
    }

    public static void listarProduto() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         LISTA DE PRODUTOS");
        System.out.println("=================================");

        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        boolean temEstoqueBaixo = false;

        System.out.printf("%-4s %-20s %-12s %-10s %-15s%n",
                "#", "Nome", "Preço", "Qtd", "Categoria");
        System.out.println("-".repeat(65));

        for (int i = 0; i < totalProdutos; i++) {
            String alerta = quantProdutos[i] <= ESTOQUE_BAIXO ? " ⚠" : "";
            String cat    = nomeCategorias[categoriaProduto[i]];

            System.out.printf("%-4d %-20s R$ %-9.2f %-10d %-15s%s%n",
                    (i + 1),
                    nomeProdutos[i],
                    precoProdutos[i],
                    quantProdutos[i],
                    cat,
                    alerta);

            if (quantProdutos[i] <= ESTOQUE_BAIXO) {
                temEstoqueBaixo = true;
            }
        }

        System.out.println("-".repeat(65));
        System.out.println("Total de produtos: " + totalProdutos);

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

        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        listarProdutosResumido();

        System.out.print("Número do produto a alterar: ");
        int num = lerInteiroPositivo();

        if (num < 1 || num > totalProdutos) {
            System.out.println("ERRO: Número de produto inválido.");
            pressionarEnter();
            return;
        }

        int idx = num - 1;
        System.out.println("\nProduto selecionado: " + nomeProdutos[idx]);
        System.out.println("Preço atual: R$ " + String.format("%.2f", precoProdutos[idx]));

        System.out.print("Novo preço (R$): ");
        double novoPreco = lerDecimalPositivo();

        if (novoPreco < 0) {
            System.out.println("ERRO: Preço não pode ser negativo.");
            pressionarEnter();
            return;
        }

        precoProdutos[idx] = novoPreco;
        System.out.println("\nProduto alterado com sucesso!");
        pressionarEnter();
    }

    public static void excluirProduto() {

        limparTela();

        System.out.println("=================================");
        System.out.println("         EXCLUIR PRODUTO");
        System.out.println("=================================");

        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        listarProdutosResumido();

        System.out.print("Número do produto a excluir: ");
        int num = lerInteiroPositivo();

        if (num < 1 || num > totalProdutos) {
            System.out.println("ERRO: Número de produto inválido.");
            pressionarEnter();
            return;
        }

        int idx = num - 1;
        System.out.println("\nProduto selecionado: " + nomeProdutos[idx]);
        System.out.print("Confirma a exclusão? (S/N): ");
        sc.nextLine(); // limpa buffer
        String confirmacao = sc.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            // Remove deslocando os elementos
            for (int i = idx; i < totalProdutos - 1; i++) {
                nomeProdutos[i]     = nomeProdutos[i + 1];
                precoProdutos[i]    = precoProdutos[i + 1];
                quantProdutos[i]    = quantProdutos[i + 1];
                categoriaProduto[i] = categoriaProduto[i + 1];
            }
            totalProdutos--;
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
        System.out.println("        INSERIR CATEGORIA");
        System.out.println("=================================");

        if (totalCategorias >= nomeCategorias.length) {
            System.out.println("ERRO: Limite máximo de categorias atingido.");
            pressionarEnter();
            return;
        }

        sc.nextLine(); // limpa buffer

        System.out.print("Nome da categoria: ");
        String nome = sc.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("ERRO: Nome não pode ser vazio.");
            pressionarEnter();
            return;
        }

        nomeCategorias[totalCategorias] = nome;
        totalCategorias++;

        System.out.println("\nCategoria cadastrada com sucesso!");
        pressionarEnter();
    }

    public static void listarCategoria() {

        limparTela();

        System.out.println("=================================");
        System.out.println("        LISTA DE CATEGORIAS");
        System.out.println("=================================");

        if (totalCategorias == 0) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        System.out.printf("%-4s %-25s %-15s%n", "#", "Nome", "Qtd. Produtos");
        System.out.println("-".repeat(48));

        for (int i = 0; i < totalCategorias; i++) {
            int qtdProdutos = contarProdutosDaCategoria(i);
            System.out.printf("%-4d %-25s %-15d%n",
                    (i + 1),
                    nomeCategorias[i],
                    qtdProdutos);
        }

        System.out.println("-".repeat(48));
        System.out.println("Total de categorias: " + totalCategorias);

        pressionarEnter();
    }

    public static void alterarCategoria() {

        limparTela();

        System.out.println("=================================");
        System.out.println("        ALTERAR CATEGORIA");
        System.out.println("=================================");

        if (totalCategorias == 0) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        listarCategoriasResumido();

        System.out.print("Número da categoria a alterar: ");
        int num = lerInteiroPositivo();

        if (num < 1 || num > totalCategorias) {
            System.out.println("ERRO: Número de categoria inválido.");
            pressionarEnter();
            return;
        }

        int idx = num - 1;
        System.out.println("\nCategoria selecionada: " + nomeCategorias[idx]);

        sc.nextLine(); // limpa buffer
        System.out.print("Novo nome: ");
        String novoNome = sc.nextLine().trim();

        if (novoNome.isEmpty()) {
            System.out.println("ERRO: Nome não pode ser vazio.");
            pressionarEnter();
            return;
        }

        nomeCategorias[idx] = novoNome;
        System.out.println("\nCategoria alterada com sucesso!");
        pressionarEnter();
    }

    public static void excluirCategoria() {

        limparTela();

        System.out.println("=================================");
        System.out.println("        EXCLUIR CATEGORIA");
        System.out.println("=================================");

        if (totalCategorias == 0) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        listarCategoriasResumido();

        System.out.print("Número da categoria a excluir: ");
        int num = lerInteiroPositivo();

        if (num < 1 || num > totalCategorias) {
            System.out.println("ERRO: Número de categoria inválido.");
            pressionarEnter();
            return;
        }

        int idx = num - 1;

        // Bloqueia exclusão se houver produtos vinculados
        int qtdProdutos = contarProdutosDaCategoria(idx);
        if (qtdProdutos > 0) {
            System.out.println("\nERRO: Não é possível excluir a categoria \"" + nomeCategorias[idx]
                    + "\" pois ela possui " + qtdProdutos + " produto(s) vinculado(s).");
            System.out.println("Remova ou realoque os produtos antes de excluir a categoria.");
            pressionarEnter();
            return;
        }

        System.out.println("\nCategoria selecionada: " + nomeCategorias[idx]);
        System.out.print("Confirma a exclusão? (S/N): ");
        sc.nextLine(); // limpa buffer
        String confirmacao = sc.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            // Reajusta índices dos produtos que apontam para categorias posteriores
            for (int i = 0; i < totalProdutos; i++) {
                if (categoriaProduto[i] > idx) {
                    categoriaProduto[i]--;
                }
            }
            // Remove a categoria deslocando elementos
            for (int i = idx; i < totalCategorias - 1; i++) {
                nomeCategorias[i] = nomeCategorias[i + 1];
            }
            totalCategorias--;
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
        System.out.println("       RELATÓRIO DE ESTOQUE");
        System.out.println("=================================");

        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            pressionarEnter();
            return;
        }

        double valorTotal     = 0;
        int    prodBaixo      = 0;

        // Calcula totais
        for (int i = 0; i < totalProdutos; i++) {
            valorTotal += precoProdutos[i] * quantProdutos[i];
            if (quantProdutos[i] <= ESTOQUE_BAIXO) {
                prodBaixo++;
            }
        }

        System.out.println("Total de produtos cadastrados : " + totalProdutos);
        System.out.printf( "Valor total em estoque        : R$ %.2f%n", valorTotal);
        System.out.println("Produtos com estoque baixo    : " + prodBaixo
                + " (≤ " + ESTOQUE_BAIXO + " unidades)");

        // Valor por categoria
        System.out.println("\n--- Valor por Categoria ---");
        System.out.printf("%-25s %-10s %-15s%n", "Categoria", "Produtos", "Valor Total");
        System.out.println("-".repeat(53));

        for (int c = 0; c < totalCategorias; c++) {
            double valorCat  = 0;
            int    qtdCat    = 0;
            for (int p = 0; p < totalProdutos; p++) {
                if (categoriaProduto[p] == c) {
                    valorCat += precoProdutos[p] * quantProdutos[p];
                    qtdCat++;
                }
            }
            System.out.printf("%-25s %-10d R$ %.2f%n",
                    nomeCategorias[c], qtdCat, valorCat);
        }

        System.out.println("-".repeat(53));

        // Produtos com estoque baixo listados
        if (prodBaixo > 0) {
            System.out.println("\n⚠  Produtos com estoque baixo:");
            for (int i = 0; i < totalProdutos; i++) {
                if (quantProdutos[i] <= ESTOQUE_BAIXO) {
                    System.out.println("   - " + nomeProdutos[i]
                            + " | Qtd: " + quantProdutos[i]
                            + " | Categoria: " + nomeCategorias[categoriaProduto[i]]);
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

        if (totalCategorias == 0) {
            System.out.println("Nenhuma categoria cadastrada.");
            pressionarEnter();
            return;
        }

        listarCategoriasResumido();

        System.out.print("Número da categoria: ");
        int num = lerInteiroPositivo();

        if (num < 1 || num > totalCategorias) {
            System.out.println("ERRO: Categoria inválida.");
            pressionarEnter();
            return;
        }

        int    idx       = num - 1;
        double valorCat  = 0;
        int    qtdItens  = 0;

        limparTela();

        System.out.println("=================================");
        System.out.println("Categoria: " + nomeCategorias[idx]);
        System.out.println("=================================");
        System.out.printf("%-4s %-20s %-12s %-10s %-15s%n",
                "#", "Produto", "Preço Unit.", "Qtd", "Subtotal");
        System.out.println("-".repeat(65));

        for (int i = 0; i < totalProdutos; i++) {
            if (categoriaProduto[i] == idx) {
                double subtotal = precoProdutos[i] * quantProdutos[i];
                valorCat += subtotal;
                qtdItens++;
                System.out.printf("%-4d %-20s R$ %-9.2f %-10d R$ %.2f%n",
                        qtdItens,
                        nomeProdutos[i],
                        precoProdutos[i],
                        quantProdutos[i],
                        subtotal);
            }
        }

        System.out.println("-".repeat(65));
        System.out.println("Total de itens   : " + qtdItens);
        System.out.printf( "Valor da categoria: R$ %.2f%n", valorCat);

        pressionarEnter();
    }

    // ==========================
    // AUXILIARES DE LISTAGEM
    // ==========================

    public static void listarProdutosResumido() {
        System.out.printf("%-4s %-20s %-10s%n", "#", "Nome", "Preço");
        System.out.println("-".repeat(38));
        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("%-4d %-20s R$ %.2f%n",
                    (i + 1), nomeProdutos[i], precoProdutos[i]);
        }
        System.out.println("-".repeat(38));
    }

    public static void listarCategoriasResumido() {
        System.out.printf("%-4s %-25s%n", "#", "Categoria");
        System.out.println("-".repeat(32));
        for (int i = 0; i < totalCategorias; i++) {
            System.out.printf("%-4d %-25s%n", (i + 1), nomeCategorias[i]);
        }
        System.out.println("-".repeat(32));
    }

    // ==========================
    // UTILITÁRIOS
    // ==========================

    public static int contarProdutosDaCategoria(int idxCategoria) {
        int count = 0;
        for (int i = 0; i < totalProdutos; i++) {
            if (categoriaProduto[i] == idxCategoria) {
                count++;
            }
        }
        return count;
    }

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
                sc.nextLine(); // descarta entrada inválida
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }

    public static double lerDecimalPositivo() {
        while (true) {
            try {
                double valor = sc.nextDouble();
                return valor; // validação de negativo fica a cargo do chamador
            } catch (Exception e) {
                sc.nextLine(); // descarta entrada inválida
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
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // aguarda ENTER
    }
}