import java.util.ArrayList;
import java.util.Scanner;

public class SistemaFolha {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Colaborador> colaboradores = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarPadrao();
                case 2 -> cadastrarComissionado();
                case 3 -> cadastrarProducao();
                case 4 -> gerarFolhaPagamento();
                case 5 -> exibirResumoFolha();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }

    // ===================== MENU =====================

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA DE FOLHA DE PAGAMENTO =====");
        System.out.println("1 - Cadastrar Colaborador Padrão");
        System.out.println("2 - Cadastrar Colaborador Comissionado");
        System.out.println("3 - Cadastrar Colaborador Produção");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("5 - Exibir Resumo da Folha");
        System.out.println("0 - Sair");
    }

    // ===================== CADASTROS =====================

    private static void cadastrarPadrao() {
        int matricula = lerMatriculaUnica();
        String nome = lerTexto("Nome: ");

        colaboradores.add(new ColaboradorPadrao(matricula, nome));
        System.out.println("Colaborador padrão cadastrado com sucesso!");
    }

    private static void cadastrarComissionado() {
        int matricula = lerMatriculaUnica();
        String nome = lerTexto("Nome: ");
        double valorVendas = lerDouble("Valor de vendas (R$): ");
        double percentual = lerDouble("Percentual de comissão (%): ");

        colaboradores.add(new ColaboradorComissionado(matricula, nome, valorVendas, percentual));
        System.out.println("Colaborador comissionado cadastrado com sucesso!");
    }

    private static void cadastrarProducao() {
        int matricula = lerMatriculaUnica();
        String nome = lerTexto("Nome: ");
        int quantidadePecas = lerInteiro("Quantidade de peças: ");
        double valorPorPeca = lerDouble("Valor por peça (R$): ");

        colaboradores.add(new ColaboradorProducao(matricula, nome, quantidadePecas, valorPorPeca));
        System.out.println("Colaborador de produção cadastrado com sucesso!");
    }

    // ===================== RELATÓRIOS =====================

    private static void gerarFolhaPagamento() {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        System.out.println("\n===== FOLHA DE PAGAMENTO =====");
        for (Colaborador c : colaboradores) {
            System.out.println("----------------------------");
            c.exibirDados();
        }
    }

    private static void exibirResumoFolha() {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        System.out.println("\n===== RESUMO DA FOLHA =====");
        System.out.println("Quantidade total de colaboradores: " + colaboradores.size());
        System.out.println("Soma total da folha: " + Colaborador.formatarMoeda(calcularTotalFolha()));
        System.out.println("Média salarial: " + Colaborador.formatarMoeda(calcularMediaSalarial()));
    }

    // ===================== CÁLCULOS REUTILIZÁVEIS =====================

    private static double calcularTotalFolha() {
        double total = 0;
        for (Colaborador c : colaboradores) {
            total += c.calcularSalarioFinal();
        }
        return total;
    }

    private static double calcularMediaSalarial() {
        if (colaboradores.isEmpty()) return 0;
        return calcularTotalFolha() / colaboradores.size();
    }

    private static boolean matriculaExiste(int matricula) {
        for (Colaborador c : colaboradores) {
            if (c.getMatricula() == matricula) return true;
        }
        return false;
    }

    // ===================== ENTRADAS VALIDADAS =====================

    private static int lerMatriculaUnica() {
        while (true) {
            int matricula = lerInteiro("Matrícula: ");
            if (!matriculaExiste(matricula)) return matricula;
            System.out.println("Essa matrícula já está cadastrada. Informe outra.");
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }
}