// Atividade 4 - Classe Cofrinho
public class Cofrinho {

    private String dono;
    private double saldo;
    private double metaEconomia;

    public Cofrinho(String dono, double metaEconomia) {
        this.dono = dono;
        this.metaEconomia = metaEconomia;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Depósito recusado: o valor deve ser positivo.");
            return;
        }
        saldo += valor;
        System.out.printf("Depósito de R$ %.2f realizado.%n", valor);
    }

    public void retirar(double valor) {
        if (valor <= 0) {
            System.out.println("Retirada recusada: o valor deve ser positivo.");
            return;
        }
        if (valor > saldo) {
            System.out.printf("Retirada recusada: saldo insuficiente (saldo: R$ %.2f, pedido: R$ %.2f).%n",
                    saldo, valor);
            return;
        }
        saldo -= valor;
        System.out.printf("Retirada de R$ %.2f realizada.%n", valor);
    }

    public void verificarMeta() {
        if (saldo >= metaEconomia) {
            System.out.printf("Parabéns, %s! Meta de R$ %.2f atingida.%n", dono, metaEconomia);
        } else {
            double falta = metaEconomia - saldo;
            System.out.printf("Meta ainda não atingida. Faltam R$ %.2f para chegar em R$ %.2f.%n",
                    falta, metaEconomia);
        }
    }

    public void exibirSaldo() {
        System.out.printf("Saldo do cofrinho de %s: R$ %.2f%n", dono, saldo);
    }
}
