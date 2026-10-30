package biblioteca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Buscar livro");
            System.out.println("4 - Cadastrar usuário");
            System.out.println("5 - Listar usuários");
            System.out.println("6 - Realizar empréstimo");
            System.out.println("7 - Devolver livro");
            System.out.println("8 - Consultar empréstimos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Código do livro: ");
                    int codigoLivro = entrada.nextInt();
                    entrada.nextLine();

                    System.out.print("Título: ");
                    String titulo = entrada.nextLine();

                    System.out.print("Autor: ");
                    String autor = entrada.nextLine();

                    biblioteca.cadastrarLivro(
                        new Livro(codigoLivro, titulo, autor)
                    );
                    break;

                case 2:
                    biblioteca.listarLivros();
                    break;

                case 3:
                    System.out.print("Digite o código do livro: ");
                    int busca = entrada.nextInt();

                    biblioteca.buscarLivro(busca);
                    break;

                case 4:
                    System.out.print("Código do usuário: ");
                    int codigoUsuario = entrada.nextInt();
                    entrada.nextLine();

                    System.out.print("Nome do usuário: ");
                    String nome = entrada.nextLine();

                    biblioteca.cadastrarUsuario(
                        new Usuario(codigoUsuario, nome)
                    );
                    break;

                case 5:
                    biblioteca.listarUsuarios();
                    break;

                case 6:
                    System.out.print("Código do livro: ");
                    int livroEmprestimo = entrada.nextInt();

                    System.out.print("Código do usuário: ");
                    int usuarioEmprestimo = entrada.nextInt();

                    biblioteca.realizarEmprestimo(
                        livroEmprestimo, usuarioEmprestimo
                    );
                    break;

                case 7:
                    System.out.print("Código do livro para devolução: ");
                    int devolucao = entrada.nextInt();

                    biblioteca.devolverLivro(devolucao);
                    break;

                case 8:
                    biblioteca.listarEmprestimos();
                    break;

                case 0:
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        entrada.close();
    }
}