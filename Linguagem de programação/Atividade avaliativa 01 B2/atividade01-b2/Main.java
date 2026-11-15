public class Main {

    public static void main(String[] args) {
        atividade1();
        atividade2();
        atividade3();
        atividade4();
        atividade5();
    }

    static void titulo(String texto) {
        System.out.println();
        System.out.println("========================================");
        System.out.println(texto);
        System.out.println("========================================");
    }

    static void atividade1() {
        titulo("ATIVIDADE 1 - ESTACIONAMENTO");

        Estacionamento carro = new Estacionamento("ABC1D23", "Onix");
        carro.adicionarTempo(40);
        carro.adicionarTempo(35);
        carro.adicionarTempo(-10);

        double valor = carro.calcularValor();
        System.out.printf("Valor calculado: R$ %.2f%n", valor);
        carro.exibirResumo();
    }

    static void atividade2() {
        titulo("ATIVIDADE 2 - MÁQUINA DE REFRIGERANTES");

        MaquinaRefrigerante maquina = new MaquinaRefrigerante(5);
        maquina.exibirEstado();

        System.out.println();
        maquina.comprar(2, 10.00);

        System.out.println();
        maquina.comprar(2, 5.00);

        System.out.println();
        maquina.comprar(10, 50.00);

        System.out.println();
        maquina.reporEstoque(10);
        maquina.exibirEstado();
    }

    static void atividade3() {
        titulo("ATIVIDADE 3 - CAMPEONATO");

        Time time1 = new Time("Cascavel FC");
        Time time2 = new Time("Toledo EC");

        time1.registrarVitoria();
        time1.registrarEmpate();
        time1.registrarDerrota();
        time1.registrarVitoria();

        time2.registrarVitoria();
        time2.registrarEmpate();
        time2.registrarEmpate();
        time2.registrarDerrota();

        time1.exibirEstatisticas();
        time2.exibirEstatisticas();

        System.out.println();
        if (time1.getPontos() > time2.getPontos()) {
            System.out.println(time1.getNome() + " está na frente com " + time1.getPontos() + " pontos.");
        } else if (time2.getPontos() > time1.getPontos()) {
            System.out.println(time2.getNome() + " está na frente com " + time2.getPontos() + " pontos.");
        } else {
            System.out.println("Empate na classificação: os dois times têm " + time1.getPontos() + " pontos.");
        }
    }

    static void atividade4() {
        titulo("ATIVIDADE 4 - COFRINHO");

        Cofrinho cofrinho = new Cofrinho("Arthur", 200.00);
        cofrinho.depositar(50.00);
        cofrinho.depositar(80.00);
        cofrinho.depositar(-20.00);
        cofrinho.exibirSaldo();

        cofrinho.retirar(500.00);
        cofrinho.exibirSaldo();

        cofrinho.verificarMeta();
    }

    static void atividade5() {
        titulo("ATIVIDADE 5 - PEDIDO DE RESTAURANTE");

        Pedido pedido = new Pedido("Arthur");
        pedido.adicionarLanches(2);
        pedido.adicionarLanches(1);

        System.out.printf("Total calculado: R$ %.2f%n", pedido.calcularTotal());
        pedido.finalizarPedido();

        System.out.println();
        boolean permitido = pedido.adicionarLanches(1);
        if (permitido) {
            System.out.println("Operação permitida.");
        } else {
            System.out.println("Operação NÃO permitida.");
        }

        System.out.println();
        pedido.exibirPedido();
    }
}
