package biblioteca;

import java.util.ArrayList;

public class Biblioteca {

    private static final int LIMITE_LIVROS = 2;

    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarLivro(String titulo, String autor) {
        livros.add(new Livro(titulo, autor));
    }

    public void cadastrarUsuario(String nome) {
        usuarios.add(new Usuario(nome));
    }

    public boolean temLivros() {
        return !livros.isEmpty();
    }

    public boolean temUsuarios() {
        return !usuarios.isEmpty();
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("\n-------- Lista de livros --------");
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            System.out.printf("%d) %s - %s (%s)%n", i + 1, livro.getTitulo(), livro.getAutor(), livro.getSituacao());
        }
    }

    public void buscarLivro(String texto) {
        boolean achou = false;

        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            String titulo = livro.getTitulo().toLowerCase();
            String autor = livro.getAutor().toLowerCase();

            if (titulo.contains(texto.toLowerCase()) || autor.contains(texto.toLowerCase())) {
                System.out.printf("%d) %s - %s (%s)%n", i + 1, livro.getTitulo(), livro.getAutor(), livro.getSituacao());
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\n-------- Lista de usuários --------");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.printf("%d) %s%n", i + 1, usuario.getNome());

            for (Livro livro : usuario.getLivros()) {
                System.out.println("   - " + livro.getTitulo());
            }
        }
    }

    public void excluirLivro(int numero) {
        if (numero < 1 || numero > livros.size()) {
            System.out.println("Livro inválido!");
            return;
        }

        Livro livro = livros.get(numero - 1);
        if (livro.isEmprestado()) {
            System.out.println("Esse livro está emprestado e não pode ser excluído!");
            return;
        }

        livros.remove(livro);
        System.out.println("Livro excluído com sucesso!");
    }

    public void emprestarLivro(int numeroUsuario, int numeroLivro) {
        if (numeroUsuario < 1 || numeroUsuario > usuarios.size()) {
            System.out.println("Usuário inválido!");
            return;
        }
        if (numeroLivro < 1 || numeroLivro > livros.size()) {
            System.out.println("Livro inválido!");
            return;
        }

        Usuario usuario = usuarios.get(numeroUsuario - 1);
        Livro livro = livros.get(numeroLivro - 1);

        if (usuario.getLivros().size() >= LIMITE_LIVROS) {
            System.out.println("Esse usuário já está com " + LIMITE_LIVROS + " livros. Devolva um para pegar outro!");
            return;
        }
        if (livro.isEmprestado()) {
            System.out.println("Esse livro já está emprestado!");
            return;
        }

        livro.setEmprestado(true);
        usuario.getLivros().add(livro);
        emprestimos.add(new Emprestimo(livro, usuario));
        System.out.println("Livro emprestado com sucesso!");
    }

    public boolean mostrarLivrosDoUsuario(int numeroUsuario) {
        if (numeroUsuario < 1 || numeroUsuario > usuarios.size()) {
            System.out.println("Usuário inválido!");
            return false;
        }

        Usuario usuario = usuarios.get(numeroUsuario - 1);
        if (usuario.getLivros().isEmpty()) {
            System.out.println("Esse usuário não tem livros emprestados.");
            return false;
        }

        for (int i = 0; i < usuario.getLivros().size(); i++) {
            System.out.printf("%d) %s%n", i + 1, usuario.getLivros().get(i).getTitulo());
        }
        return true;
    }

    public void devolverLivro(int numeroUsuario, int numeroLivro) {
        Usuario usuario = usuarios.get(numeroUsuario - 1);

        if (numeroLivro < 1 || numeroLivro > usuario.getLivros().size()) {
            System.out.println("Livro inválido!");
            return;
        }

        Livro livro = usuario.getLivros().get(numeroLivro - 1);

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro() == livro && emprestimo.isAtivo()) {
                emprestimo.devolver();
            }
        }

        livro.setEmprestado(false);
        usuario.getLivros().remove(livro);
        System.out.println("Livro devolvido com sucesso!");
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo feito ainda.");
            return;
        }

        System.out.println("\n-------- Histórico de empréstimos --------");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo.getDados());
        }
    }
}
