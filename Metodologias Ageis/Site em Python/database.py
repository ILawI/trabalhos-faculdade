import sqlite3

NOME_BANCO = "banco.db"

def conectar():
    """Abre (ou cria, se nao existir) o arquivo do banco e retorna a conexao."""
    conexao = sqlite3.connect(NOME_BANCO)
    conexao.row_factory = sqlite3.Row
    return conexao

def criar_tabelas():
    """Cria as tabelas usuarios e eventos, se ainda nao existirem."""
    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
        CREATE TABLE IF NOT EXISTS usuarios (
            id          INTEGER PRIMARY KEY AUTOINCREMENT,
            usuario     TEXT UNIQUE NOT NULL,
            senha_hash  TEXT NOT NULL
        )
    """)

    cursor.execute("""
        CREATE TABLE IF NOT EXISTS eventos (
            id           INTEGER PRIMARY KEY AUTOINCREMENT,
            titulo       TEXT NOT NULL,
            palestrante  TEXT NOT NULL,
            local        TEXT,              -- preenchido se presencial
            link         TEXT,              -- preenchido se remoto
            data_hora    TEXT NOT NULL      -- formato ISO: AAAA-MM-DD HH:MM
        )
    """)

    conexao.commit()
    conexao.close()
    print("Tabelas criadas com sucesso!")

if __name__ == "__main__":
    criar_tabelas()

    