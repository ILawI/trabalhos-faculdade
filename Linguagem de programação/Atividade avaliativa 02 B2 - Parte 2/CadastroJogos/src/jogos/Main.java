package jogos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Jogo> jogos = new ArrayList<>();
    static int proximoId = 1;

    public static void main(String[] args) {
        int opcao;

        jogos.add(new Jogo(proximoId++, "GTA V", "Ação", "PC"));
        jogos.add(new Jogo(proximoId++, "ARK: Survival Ascended", "Sobrevivência", "PC"));
        jogos.add(new Jogo(proximoId++, "Valorant", "Tiro", "PC"));

        do {
            System.out.println("\n========== SISTEMA DE GERENCIAMENTO ==========");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
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
    }

    static int lerNumero() {
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    static void mostrar(Jogo jogo) {
        System.out.println(jogo.getId() + " - " + jogo.getNome() + " | " + jogo.getGenero() + " | " + jogo.getPlataforma());
    }

    static Jogo buscarPorId() {
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return null;
        }

        System.out.print("Digite o ID: ");
        int id = lerNumero();

        for (Jogo jogo : jogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }

        System.out.println("ID não encontrado.");
        return null;
    }

    static void cadastrar() {
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Gênero: ");
        String genero = scan.nextLine();
        System.out.print("Plataforma: ");
        String plataforma = scan.nextLine();

        jogos.add(new Jogo(proximoId++, nome, genero, plataforma));
        System.out.println("Jogo cadastrado!");
    }

    static void listar() {
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }

        for (Jogo jogo : jogos) {
            mostrar(jogo);
        }
    }

    static void buscar() {
        Jogo jogo = buscarPorId();

        if (jogo != null) {
            mostrar(jogo);
        }
    }

    static void atualizar() {
        Jogo jogo = buscarPorId();

        if (jogo == null) {
            return;
        }

        System.out.print("Novo nome: ");
        jogo.setNome(scan.nextLine());
        System.out.print("Novo gênero: ");
        jogo.setGenero(scan.nextLine());
        System.out.print("Nova plataforma: ");
        jogo.setPlataforma(scan.nextLine());
        System.out.println("Jogo atualizado!");
    }

    static void excluir() {
        Jogo jogo = buscarPorId();

        if (jogo != null) {
            jogos.remove(jogo);
            System.out.println("Jogo excluído!");
        }
    }
}
