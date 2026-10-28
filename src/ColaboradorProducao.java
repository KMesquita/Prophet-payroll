
public class ColaboradorProducao extends Colaborador {

    private int quantidadeProdutos;
    private double valorPorProduto;

    public ColaboradorProducao(int matricula, String nome,
                               int quantidadeProdutos, double valorPorProduto) {
        super(matricula, nome);
        this.quantidadeProdutos = quantidadeProdutos;
        this.valorPorProduto = valorPorProduto;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public void setQuantidadeProdutos(int quantidadeProdutos) {
        this.quantidadeProdutos = quantidadeProdutos;
    }

    public double getValorPorProduto() {
        return valorPorProduto;
    }

    public void setValorPorProduto(double valorPorProduto) {
        this.valorPorProduto = valorPorProduto;
    }

    // Produtividade = quantidade de produtos × valor por produto
    public double calcularProdutividade() {
        return quantidadeProdutos * valorPorProduto;
    }

    @Override
    public String getTipo() {
        return "Produção";
    }

    @Override
    public double getBeneficioAdicional() {
        return calcularProdutividade();
    }

    // Salário final = salário-base + produtividade
    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularProdutividade();
    }
}
