package biblioteca;

import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            livro.exibirDados();
        }
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios) {
            usuario.exibirDados();
        }
    }

    public void realizarEmprestimo(int codigoLivro, int codigoUsuario) {

        Livro livroEncontrado = null;
        Usuario usuarioEncontrado = null;

        for (Livro livro : livros) {
            if (livro.getCodigo() == codigoLivro) {
                livroEncontrado = livro;
                break;
            }
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigoUsuario) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (livroEncontrado == null || usuarioEncontrado == null) {
            System.out.println("Livro ou usuário não encontrado.");
            return;
        }

        if (!livroEncontrado.isDisponivel()) {
            System.out.println("Este livro já está emprestado.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(livroEncontrado, usuarioEncontrado);
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
    }

    public void devolverLivro(int codigoLivro) {

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getLivro().getCodigo() == codigoLivro
                    && emprestimo.isAtivo()) {

                emprestimo.devolver();
                System.out.println("Livro devolvido com sucesso!");
                return;
            }
        }

        System.out.println("Empréstimo ativo não encontrado.");
    }

    public void listarEmprestimos() {

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        for (Emprestimo emprestimo : emprestimos) {
            emprestimo.exibirDados();
        }
    }

    public void buscarLivro(int codigo) {

        for (Livro livro : livros) {
            if (livro.getCodigo() == codigo) {
                livro.exibirDados();
                return;
            }
        }

        System.out.println("Livro não encontrado.");
    }
}