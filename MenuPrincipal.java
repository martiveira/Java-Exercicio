import java.util.Scanner;

public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);

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
            System.out.println("      SISTEMA DE ESTOQUE");
            System.out.println("=================================");
            System.out.println("1. Gerenciar Produtos");
            System.out.println("2. Gerenciar Categorias");
            System.out.println("3. Relatórios");
            System.out.println("0. Sair");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    menuProduto();
                    break;

                case 2:
                    menuCategoria();
                    break;

                case 3:
                    relatorio();
                    break;

                case 0:
                    limparTela();
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
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
            System.out.println("         MENU PRODUTO");
            System.out.println("=================================");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Alterar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("0. Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    listarProduto();
                    break;

                case 3:
                    alterarProduto();
                    break;

                case 4:
                    excluirProduto();
                    break;

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
            System.out.println("        MENU CATEGORIA");
            System.out.println("=================================");
            System.out.println("1. Cadastrar Categoria");
            System.out.println("2. Listar Categorias");
            System.out.println("3. Alterar Categoria");
            System.out.println("4. Excluir Categoria");
            System.out.println("0. Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    cadastrarCategoria();
                    break;

                case 2:
                    listarCategoria();
                    break;

                case 3:
                    alterarCategoria();
                    break;

                case 4:
                    excluirCategoria();
                    break;

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
    // RELATÓRIOS
    // ==========================
    public static void relatorio() {

        limparTela();

        System.out.println("=================================");
        System.out.println("          RELATÓRIOS");
        System.out.println("=================================");
        System.out.println("Relatório gerado com sucesso.");
        System.out.println("=================================");

        pressionarEnter();
    }

    // ==========================
    // CRUD PRODUTO
    // ==========================
    public static void cadastrarProduto() {

        limparTela();

        System.out.println("Produto cadastrado com sucesso!");

        pressionarEnter();
    }

    public static void listarProduto() {

        limparTela();

        System.out.println("Listando produtos...");

        pressionarEnter();
    }

    public static void alterarProduto() {

        limparTela();

        System.out.println("Produto alterado com sucesso!");

        pressionarEnter();
    }

    public static void excluirProduto() {

        limparTela();

        System.out.println("Produto excluído com sucesso!");

        pressionarEnter();
    }

    // ==========================
    // CRUD CATEGORIA
    // ==========================
    public static void cadastrarCategoria() {

        limparTela();

        System.out.println("Categoria cadastrada com sucesso!");

        pressionarEnter();
    }

    public static void listarCategoria() {

        limparTela();

        System.out.println("Listando categorias...");

        pressionarEnter();
    }

    public static void alterarCategoria() {

        limparTela();

        System.out.println("Categoria alterada com sucesso!");

        pressionarEnter();
    }

    public static void excluirCategoria() {

        limparTela();

        System.out.println("Categoria excluída com sucesso!");

        pressionarEnter();
    }

    // ==========================
    // UTILITÁRIOS
    // ==========================
    public static void limparTela() {

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pressionarEnter() {

        System.out.println("\nPressione ENTER para continuar...");

        sc.nextLine(); // limpa buffer
        sc.nextLine(); // espera ENTER
    }
}
