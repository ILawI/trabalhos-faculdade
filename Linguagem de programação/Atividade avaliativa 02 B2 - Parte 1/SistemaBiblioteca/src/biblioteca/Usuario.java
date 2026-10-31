package biblioteca;

import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Livro> livros = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }
}
