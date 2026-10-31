package biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Livro> livros = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        usuarios.add(new Usuario("Ana"));
        usuarios.add(new Usuario("Bruno"));
        usuarios.add(new Usuario("Carla"));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis"));
        livros.add(new Livro("O Cortiço", "Aluísio Azevedo"));
        livros.add(new Livro("Vidas Secas", "Graciliano Ramos"));
        livros.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
        livros.add(new Livro("O Hobbit", "J. R. R. Tolkien"));

        do {
            System.out.println("\n========== BIBLIOTECA ==========");
            System.out.println("1 - Listar livros");
            System.out.println("2 - Cadastrar livro");
            System.out.println("3 - Excluir livro");
            System.out.println("4 - Emprestar livro");
            System.out.println("5 - Devolver livro");
            System.out.println("6 - Cadastrar usuário");
            System.out.println("7 - Listar usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerNumero();

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    cadastrarLivro();
                    break;
                case 3:
                    excluirLivro();
                    break;
                case 4:
                    emprestarLivro();
                    break;
                case 5:
                    devolverLivro();
                    break;
                case 6:
                    cadastrarUsuario();
                    break;
                case 7:
                    listarUsuarios();
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

    static void listarLivros() {
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            String situacao = livro.isEmprestado() ? "Emprestado" : "Disponível";
            System.out.println((i + 1) + ") " + livro.getTitulo() + " - " + livro.getAutor() + " (" + situacao + ")");
        }
    }

    static void listarUsuarios() {
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ") " + usuarios.get(i).getNome());
            for (Livro livro : usuarios.get(i).getLivros()) {
                System.out.println("   - " + livro.getTitulo());
            }
        }
    }

    static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scan.nextLine();
        System.out.print("Autor: ");
        String autor = scan.nextLine();

        livros.add(new Livro(titulo, autor));
        System.out.println("Livro cadastrado!");
    }

    static void cadastrarUsuario() {
        System.out.print("Nome: ");
        usuarios.add(new Usuario(scan.nextLine()));
        System.out.println("Usuário cadastrado!");
    }

    static void excluirLivro() {
        listarLivros();
        System.out.print("Número do livro: ");
        int l = lerNumero() - 1;

        if (l < 0 || l >= livros.size()) {
            System.out.println("Livro inválido!");
        } else if (livros.get(l).isEmprestado()) {
            System.out.println("Livro emprestado não pode ser excluído!");
        } else {
            livros.remove(l);
            System.out.println("Livro excluído!");
        }
    }

    static void emprestarLivro() {
        listarUsuarios();
        System.out.print("Número do usuário: ");
        int u = lerNumero() - 1;

        if (u < 0 || u >= usuarios.size()) {
            System.out.println("Usuário inválido!");
            return;
        }
        if (usuarios.get(u).getLivros().size() == 2) {
            System.out.println("Esse usuário já tem 2 livros. Devolva um antes!");
            return;
        }

        listarLivros();
        System.out.print("Número do livro: ");
        int l = lerNumero() - 1;

        if (l < 0 || l >= livros.size()) {
            System.out.println("Livro inválido!");
        } else if (livros.get(l).isEmprestado()) {
            System.out.println("Esse livro já está emprestado!");
        } else {
            livros.get(l).setEmprestado(true);
            usuarios.get(u).getLivros().add(livros.get(l));
            System.out.println("Livro emprestado!");
        }
    }

    static void devolverLivro() {
        listarUsuarios();
        System.out.print("Número do usuário: ");
        int u = lerNumero() - 1;

        if (u < 0 || u >= usuarios.size()) {
            System.out.println("Usuário inválido!");
            return;
        }

        ArrayList<Livro> livrosDoUsuario = usuarios.get(u).getLivros();
        if (livrosDoUsuario.isEmpty()) {
            System.out.println("Esse usuário não tem livros emprestados.");
            return;
        }

        for (int i = 0; i < livrosDoUsuario.size(); i++) {
            System.out.println((i + 1) + ") " + livrosDoUsuario.get(i).getTitulo());
        }
        System.out.print("Número do livro: ");
        int l = lerNumero() - 1;

        if (l < 0 || l >= livrosDoUsuario.size()) {
            System.out.println("Livro inválido!");
        } else {
            livrosDoUsuario.get(l).setEmprestado(false);
            livrosDoUsuario.remove(l);
            System.out.println("Livro devolvido!");
        }
    }
}
