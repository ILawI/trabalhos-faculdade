// Atividade 5 - Classe Pedido de Restaurante
public class Pedido {

    private String nomeCliente;
    private int quantidadeLanches;
    private double precoLanche;
    private double taxaEntrega;
    private String status; // "ABERTO" ou "FINALIZADO"

    public Pedido(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        this.quantidadeLanches = 0;
        this.precoLanche = 18.00;
        this.taxaEntrega = 7.00;
        this.status = "ABERTO";
    }

    // Retorna true se conseguiu adicionar e false se a operação foi bloqueada.
    public boolean adicionarLanches(int quantidade) {
        if (status.equals("FINALIZADO")) {
            System.out.println("Não é possível adicionar lanches: o pedido já foi finalizado.");
            return false;
        }
        if (quantidade <= 0) {
            System.out.println("A quantidade de lanches deve ser maior que zero.");
            return false;
        }
        quantidadeLanches += quantidade;
        System.out.println(quantidade + " lanche(s) adicionado(s). Total no pedido: " + quantidadeLanches + ".");
        return true;
    }

    // Total = valor dos lanches (com 10% de desconto se forem 3 ou mais) + taxa de entrega.
    // O desconto é só sobre os lanches, não sobre a entrega.
    public double calcularTotal() {
        double valorLanches = quantidadeLanches * precoLanche;

        if (quantidadeLanches >= 3) {
            valorLanches = valorLanches * 0.90;
        }

        return valorLanches + taxaEntrega;
    }

    public void finalizarPedido() {
        status = "FINALIZADO";
        System.out.println("Pedido de " + nomeCliente + " finalizado.");
    }

    public void exibirPedido() {
        double valorSemDesconto = quantidadeLanches * precoLanche;

        System.out.println("---- Pedido ----");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Lanches: " + quantidadeLanches + " x R$ " + String.format("%.2f", precoLanche));
        System.out.printf("Subtotal dos lanches: R$ %.2f%n", valorSemDesconto);
        if (quantidadeLanches >= 3) {
            System.out.printf("Desconto (10%%): -R$ %.2f%n", valorSemDesconto * 0.10);
        }
        System.out.printf("Taxa de entrega: R$ %.2f%n", taxaEntrega);
        System.out.printf("Total: R$ %.2f%n", calcularTotal());
        System.out.println("Status: " + status);
    }
}
