package jogos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Jogo> jogos = new ArrayList<>();
    static int proximoId = 1;

    public static void main(String[] args) {
        int opcao;

        cadastrarJogosIniciais();

        do {
            mostrarMenu();
            opcao = lerNumero();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    atualizar();
                    break;
                case 5:
                    excluir();
                    break;
                case 0:
                    System.out.println("Sistema encerrado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scan.close();
    }

    private static void cadastrarJogosIniciais() {
        jogos.add(new Jogo(proximoId++, "GTA V", "Ação / Mundo aberto", "PC"));
        jogos.add(new Jogo(proximoId++, "ARK: Survival Ascended", "Sobrevivência", "PC"));
        jogos.add(new Jogo(proximoId++, "Valorant", "Tiro (FPS)", "PC"));
    }

    private static void mostrarMenu() {
        System.out.println("\n========== SISTEMA DE GERENCIAMENTO ==========");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar todos");
        System.out.println("3 - Buscar por ID");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Excluir");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerNumero() {
        try {
            return Integer.parseInt(scan.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String lerTexto(String mensagem) {
        String texto;

        do {
            System.out.print(mensagem);
            texto = scan.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Esse campo não pode ficar vazio!");
            }
        } while (texto.isEmpty());

        return texto;
    }

    private static Jogo procurarPorId(int id) {
        for (Jogo jogo : jogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }
        return null;
    }

    private static Jogo pedirJogo() {
        Jogo jogo;

        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return null;
        }

        System.out.print("Digite o ID do jogo: ");
        jogo = procurarPorId(lerNumero());

        if (jogo == null) {
            System.out.println("Não existe jogo com esse ID.");
        }
        return jogo;
    }

    private static void cadastrar() {
        String nome = lerTexto("Nome: ");
        String genero = lerTexto("Gênero: ");
        String plataforma = lerTexto("Plataforma: ");

        Jogo jogo = new Jogo(proximoId, nome, genero, plataforma);
        jogos.add(jogo);
        proximoId++;

        System.out.println("Jogo cadastrado com o ID " + jogo.getId() + "!");
    }

    private static void listar() {
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }

        System.out.println("\n---------- Lista de jogos ----------");
        for (Jogo jogo : jogos) {
            jogo.exibir();
        }
    }

    private static void buscar() {
        Jogo jogo = pedirJogo();

        if (jogo != null) {
            jogo.exibir();
        }
    }

    private static void atualizar() {
        Jogo jogo = pedirJogo();

        if (jogo == null) {
            return;
        }

        jogo.exibir();
        System.out.println("Digite os novos dados:");
        jogo.setNome(lerTexto("Nome: "));
        jogo.setGenero(lerTexto("Gênero: "));
        jogo.setPlataforma(lerTexto("Plataforma: "));

        System.out.println("Jogo atualizado com sucesso!");
    }

    private static void excluir() {
        Jogo jogo = pedirJogo();

        if (jogo == null) {
            return;
        }

        jogos.remove(jogo);
        System.out.println("Jogo " + jogo.getNome() + " excluído com sucesso!");
    }
}
