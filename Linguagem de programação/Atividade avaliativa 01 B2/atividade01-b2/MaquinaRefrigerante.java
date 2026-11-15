public class MaquinaRefrigerante {

    private int quantidadeEstoque;
    private double preco;
    private double dinheiroArrecadado;

    public MaquinaRefrigerante(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = 4.50;
        this.dinheiroArrecadado = 0.0;
    }

    public boolean comprar(int quantidade, double valorPago) {
        System.out.println("Tentando comprar " + quantidade + " refrigerante(s) pagando R$ "
                + String.format("%.2f", valorPago) + "...");

        if (quantidade <= 0) {
            System.out.println("Compra recusada: a quantidade deve ser maior que zero.");
            return false;
        }

        if (quantidade > quantidadeEstoque) {
            System.out.println("Compra recusada: estoque insuficiente (disponível: "
                    + quantidadeEstoque + ").");
            return false;
        }

        double valorTotal = quantidade * preco;

        if (valorPago < valorTotal) {
            System.out.printf("Compra recusada: dinheiro insuficiente. Total: R$ %.2f, faltam R$ %.2f.%n",
                    valorTotal, valorTotal - valorPago);
            return false;
        }

        quantidadeEstoque -= quantidade;
        dinheiroArrecadado += valorTotal;

        System.out.printf("Compra realizada! Total: R$ %.2f.%n", valorTotal);

        double troco = valorPago - valorTotal;
        if (troco > 0) {
            System.out.printf("Troco: R$ %.2f%n", troco);
        }
        return true;
    }

    public void reporEstoque(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Erro: a reposição deve ser de uma quantidade positiva.");
            return;
        }
        quantidadeEstoque += quantidade;
        System.out.println("Estoque reposto com " + quantidade + " unidade(s). Estoque atual: "
                + quantidadeEstoque + ".");
    }

    public void exibirEstado() {
        System.out.println("---- Estado da Máquina ----");
        System.out.println("Estoque: " + quantidadeEstoque + " unidade(s)");
        System.out.printf("Preço: R$ %.2f%n", preco);
        System.out.printf("Dinheiro arrecadado: R$ %.2f%n", dinheiroArrecadado);
    }
}
