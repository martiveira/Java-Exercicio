import java.util.Scanner;

public class SistemaEscolar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] aluno = cadastrarAluno(sc);
        double[] notas = cadastrarNotas(sc);
        double media = calcularMedia(notas);
        String situacao = verificarSituacao(media);

        exibirBoletim(aluno[0], aluno[1], media, situacao);

        sc.close();
    }

    // Cadastrar aluno
    public static String[] cadastrarAluno(Scanner sc) {
        System.out.print("Nome do aluno: ");
        String nome = sc.nextLine();

        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();

        return new String[]{nome, matricula};
    }

    // Cadastrar notas
    public static double[] cadastrarNotas(Scanner sc) {
        System.out.print("Quantas notas? ");
        int qtd = sc.nextInt();

        double[] notas = new double[qtd];

        for (int i = 0; i < qtd; i++) {
            System.out.print("Nota " + (i + 1) + ": ");
            notas[i] = sc.nextDouble();
        }

        return notas;
    }

    // Calcular média
    public static double calcularMedia(double[] notas) {
        double soma = 0;

        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.length;
    }

    // Verificar situação
    public static String verificarSituacao(double media) {
        if (media >= 7.0) {
            return "Aprovado";
        } else if (media >= 5.0) {
            return "Recuperação";
        } else {
            return "Reprovado";
        }
    }

    // Exibir boletim
    public static void exibirBoletim(String nome, String matricula,
                                     double media, String situacao) {
        System.out.println("\n=== BOLETIM ===");
        System.out.println("Aluno: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.printf("Média: %.2f%n", media);
        System.out.println("Situação: " + situacao);
    }
}