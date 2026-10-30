package biblioteca;

public class Emprestimo {

    private Livro livro;
    private Usuario usuario;
    private boolean ativo;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.ativo = true;
        livro.setDisponivel(false);
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void devolver() {
        ativo = false;
        livro.setDisponivel(true);
    }

    public void exibirDados() {
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Situação: " + (ativo ? "Emprestado" : "Devolvido"));
        System.out.println("--------------------------");
    }
}