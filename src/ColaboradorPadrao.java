public class ColaboradorPadrao extends Colaborador {

    public ColaboradorPadrao(int matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public String getTipo() {
        return "Padrão";
    }

    @Override
    public double getBeneficioAdicional() {
        return 0.0;
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase();
    }
}