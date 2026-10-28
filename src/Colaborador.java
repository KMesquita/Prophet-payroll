import java.text.NumberFormat;
import java.util.Locale;

public abstract class Colaborador {

    // Constante do salário base (R$ 2.000,00 para todos)
    public static final double SALARIO_BASE = 2000.00;

    // Encapsulamento: atributos privados
    private int matricula;
    private String nome;
    private double salarioBase;

    public Colaborador(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        this.salarioBase = SALARIO_BASE;
    }

    // Métodos de acesso (getters e setters)
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    // Métodos que cada subclasse especializa
    public abstract String getTipo();
    public abstract double getBeneficioAdicional();
    public abstract double calcularSalarioFinal();

    public void exibirDados() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipo());
        System.out.println("Salário Base: " + formatarMoeda(salarioBase));
        if (getBeneficioAdicional() > 0) {
            System.out.println("Benefício adicional: " + formatarMoeda(getBeneficioAdicional()));
        }
        System.out.println("Salário Final: " + formatarMoeda(calcularSalarioFinal()));
    }

    public static String formatarMoeda(double valor) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
    }
}