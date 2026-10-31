package biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {

    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isAtivo() {
        return dataDevolucao == null;
    }

    public void devolver() {
        dataDevolucao = LocalDate.now();
    }

    public String getDados() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String texto = livro.getTitulo() + " - " + usuario.getNome()
                + " | Emprestado em " + dataEmprestimo.format(formato);

        if (isAtivo()) {
            return texto + " | Ainda não devolvido";
        }
        return texto + " | Devolvido em " + dataDevolucao.format(formato);
    }
}
