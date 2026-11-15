from flask import Flask, request, jsonify, session, send_from_directory
from werkzeug.security import check_password_hash
from database import conectar, criar_tabelas

app = Flask(__name__, static_folder="frontend", static_url_path="")

app.secret_key = "troque-isto-por-algo-secreto-e-aleatorio"

criar_tabelas()

@app.route("/")
def pagina_inicial():
    return send_from_directory("frontend", "index.html")

@app.route("/api/login", methods=["POST"])
def login():
    dados = request.get_json()
    usuario = dados.get("usuario")
    senha = dados.get("senha")

    conexao = conectar()
    linha = conexao.execute(
        "SELECT * FROM usuarios WHERE usuario = ?", (usuario,)
    ).fetchone()
    conexao.close()

    if linha and check_password_hash(linha["senha_hash"], senha):
        session["logado"] = True
        session["usuario"] = usuario
        return jsonify({"ok": True})
    else:
        return jsonify({"ok": False, "erro": "Credenciais invalidas"}), 401

@app.route("/api/logout", methods=["POST"])
def logout():
    session.clear()
    return jsonify({"ok": True})

@app.route("/api/eventos", methods=["GET"])
def listar_eventos():
    conexao = conectar()

    linhas = conexao.execute(
        "SELECT * FROM eventos ORDER BY data_hora ASC"
    ).fetchall()
    conexao.close()

    eventos = []
    for linha in linhas:
        eventos.append({
            "id": linha["id"],
            "titulo": linha["titulo"],
            "palestrante": linha["palestrante"],
            "local": linha["local"],
            "link": linha["link"],
            "data_hora": linha["data_hora"],
        })

    return jsonify(eventos)

def exige_login():
    """Retorna True se NAO estiver logado (para barrar a acao)."""
    return not session.get("logado")

@app.route("/api/eventos", methods=["POST"])
def criar_evento():
    if exige_login():
        return jsonify({"erro": "Faca login primeiro"}), 401

    d = request.get_json()
    conexao = conectar()
    conexao.execute(
        """INSERT INTO eventos (titulo, palestrante, local, link, data_hora)
           VALUES (?, ?, ?, ?, ?)""",
        (d["titulo"], d["palestrante"], d.get("local"),
         d.get("link"), d["data_hora"])
    )
    conexao.commit()
    conexao.close()
    return jsonify({"ok": True}), 201

@app.route("/api/eventos/<int:evento_id>", methods=["DELETE"])
def remover_evento(evento_id):
    if exige_login():
        return jsonify({"erro": "Faca login primeiro"}), 401

    conexao = conectar()
    conexao.execute("DELETE FROM eventos WHERE id = ?", (evento_id,))
    conexao.commit()
    conexao.close()
    return jsonify({"ok": True})

if __name__ == "__main__":
    app.run(debug=True, port=5000)