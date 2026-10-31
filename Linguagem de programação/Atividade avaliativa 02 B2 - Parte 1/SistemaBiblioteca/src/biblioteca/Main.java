package biblioteca;

import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        int opcao;

        popularDados();
        System.out.println("Sistema da biblioteca iniciado!");

        do {
            mostrarMenu();
            opcao = lerNumero();
            escolherOpcao(opcao);
        } while (opcao != 0);

        scan.close();
    }

    private static void popularDados() {
        biblioteca.cadastrarUsuario("Ana");
        biblioteca.cadastrarUsuario("Bruno");
        biblioteca.cadastrarUsuario("Carla");
        biblioteca.cadastrarUsuario("Diego");

        biblioteca.cadastrarLivro("Dom Casmurro", "Machado de Assis");
        biblioteca.cadastrarLivro("Memórias Póstumas de Brás Cubas", "Machado de Assis");
        biblioteca.cadastrarLivro("O Cortiço", "Aluísio Azevedo");
        biblioteca.cadastrarLivro("Capitães da Areia", "Jorge Amado");
        biblioteca.cadastrarLivro("Vidas Secas", "Graciliano Ramos");
        biblioteca.cadastrarLivro("O Pequeno Príncipe", "Antoine de Saint-Exupéry");
        biblioteca.cadastrarLivro("Harry Potter e a Pedra Filosofal", "J. K. Rowling");
        biblioteca.cadastrarLivro("O Hobbit", "J. R. R. Tolkien");
        biblioteca.cadastrarLivro("1984", "George Orwell");
        biblioteca.cadastrarLivro("A Revolução dos Bichos", "George Orwell");
    }

    private static void mostrarMenu() {
        System.out.println("\n========== BIBLIOTECA ==========");
        System.out.println("1 - Listar livros");
        System.out.println("2 - Buscar livro");
        System.out.println("3 - Cadastrar livro");
        System.out.println("4 - Excluir livro");
        System.out.println("5 - Cadastrar usuário");
        System.out.println("6 - Listar usuários");
        System.out.println("7 - Emprestar livro");
        System.out.println("8 - Devolver livro");
        System.out.println("9 - Histórico de empréstimos");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void escolherOpcao(int opcao) {
        switch (opcao) {
            case 1:
                biblioteca.listarLivros();
                break;
            case 2:
                buscarLivro();
                break;
            case 3:
                cadastrarLivro();
                break;
            case 4:
                excluirLivro();
                break;
            case 5:
                cadastrarUsuario();
                break;
            case 6:
                biblioteca.listarUsuarios();
                break;
            case 7:
                emprestarLivro();
                break;
            case 8:
                devolverLivro();
                break;
            case 9:
                biblioteca.listarEmprestimos();
                break;
            case 0:
                System.out.println("Sistema encerrado. Obrigado por utilizar!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static int lerNumero() {
        try {
            return Integer.parseInt(scan.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void buscarLivro() {
        System.out.print("Digite o título ou o autor: ");
        String texto = scan.nextLine();
        biblioteca.buscarLivro(texto);
    }

    private static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scan.nextLine();
        System.out.print("Autor: ");
        String autor = scan.nextLine();

        if (titulo.isBlank() || autor.isBlank()) {
            System.out.println("Título e autor não podem ficar em branco!");
            return;
        }

        biblioteca.cadastrarLivro(titulo, autor);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void excluirLivro() {
        if (!biblioteca.temLivros()) {
            System.out.println("Não tem livros para excluir.");
            return;
        }

        biblioteca.listarLivros();
        System.out.print("Número do livro que vai ser excluído: ");
        biblioteca.excluirLivro(lerNumero());
    }

    private static void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scan.nextLine();

        if (nome.isBlank()) {
            System.out.println("O nome não pode ficar em branco!");
            return;
        }

        biblioteca.cadastrarUsuario(nome);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void emprestarLivro() {
        int usuario;

        if (!biblioteca.temUsuarios() || !biblioteca.temLivros()) {
            System.out.println("Precisa ter pelo menos um usuário e um livro cadastrado.");
            return;
        }

        biblioteca.listarUsuarios();
        System.out.print("Número do usuário que vai pegar o livro: ");
        usuario = lerNumero();

        biblioteca.listarLivros();
        System.out.print("Número do livro: ");
        biblioteca.emprestarLivro(usuario, lerNumero());
    }

    private static void devolverLivro() {
        int usuario;

        if (!biblioteca.temUsuarios()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        biblioteca.listarUsuarios();
        System.out.print("Número do usuário que vai devolver: ");
        usuario = lerNumero();

        if (!biblioteca.mostrarLivrosDoUsuario(usuario)) {
            return;
        }

        System.out.print("Número do livro que vai ser devolvido: ");
        biblioteca.devolverLivro(usuario, lerNumero());
    }
}
