// Atividade 1 - Classe Estacionamento
public class Estacionamento {

    // Regras de cobrança guardadas como constantes, assim fica fácil mudar depois
    private static final int MINUTOS_INICIAIS = 30;
    private static final double VALOR_INICIAL = 5.00;
    private static final double VALOR_POR_MINUTO_EXTRA = 0.10;
    private static final double VALOR_MAXIMO = 30.00;

    private String placa;
    private String modelo;
    private int minutosEstacionado;
    private double valorPago;

    public Estacionamento(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
        this.minutosEstacionado = 0;
        this.valorPago = 0.0;
    }

    // Aumenta o tempo estacionado. Não aceita valor negativo,
    // senão o tempo total poderia ficar negativo.
    public void adicionarTempo(int minutos) {
        if (minutos < 0) {
            System.out.println("Erro: não é possível adicionar tempo negativo (" + minutos + " min).");
            return;
        }
        minutosEstacionado += minutos;
        System.out.println("Adicionados " + minutos + " min. Total: " + minutosEstacionado + " min.");
    }

    // Calcula o valor a pagar:
    // até 30 min -> R$ 5,00
    // acima disso -> R$ 5,00 + R$ 0,10 por minuto extra
    // nunca passa de R$ 30,00
    public double calcularValor() {
        double valor;

        if (minutosEstacionado <= MINUTOS_INICIAIS) {
            valor = VALOR_INICIAL;
        } else {
            int minutosExtras = minutosEstacionado - MINUTOS_INICIAIS;
            valor = VALOR_INICIAL + minutosExtras * VALOR_POR_MINUTO_EXTRA;
        }

        if (valor > VALOR_MAXIMO) {
            valor = VALOR_MAXIMO;
        }

        valorPago = valor;
        return valor;
    }

    public void exibirResumo() {
        System.out.println("---- Resumo do Estacionamento ----");
        System.out.println("Placa: " + placa);
        System.out.println("Modelo: " + modelo);
        System.out.println("Tempo estacionado: " + minutosEstacionado + " min");
        System.out.printf("Valor a pagar: R$ %.2f%n", valorPago);
    }
}
