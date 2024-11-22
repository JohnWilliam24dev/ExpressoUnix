import subprocess
import json

# Configurando a URL e os dados do JSON
url = "http://localhost:5000/api/registrar_passagem"
dados = {
    "passageiro": "Alan",
    "origem": "São Paulo",
    "destino": "Rio de Janeiro",
    "data_viagem": "2024-12-25"
}

# Convertendo os dados para JSON (string)
dados_json = json.dumps(dados)

# Montando o comando cURL
comando = [
    "curl", "-X", "POST", url,
    "-H", "Content-Type: application/json",
    "-d", dados_json
]

# Executando o comando
resultado = subprocess.run(comando, capture_output=True, text=True)

# Exibindo a resposta
print("Status Code:", resultado.returncode)
print("Resposta do Servidor:", resultado.stdout)