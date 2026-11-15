from werkzeug.security import generate_password_hash
from database import conectar

def criar_administrador(usuario, senha):
    """Cria um administrador com a senha guardada de forma segura (hash)."""
    conexao = conectar()
    cursor = conexao.cursor()

    senha_protegida = generate_password_hash(senha)

    cursor.execute(
        "INSERT INTO usuarios (usuario, senha_hash) VALUES (?, ?)",
        (usuario, senha_protegida)
    )

    conexao.commit()
    conexao.close()
    print(f"Administrador '{usuario}' criado com sucesso!")

if __name__ == "__main__":
    criar_administrador("admin", "admsupr")