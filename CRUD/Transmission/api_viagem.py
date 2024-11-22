from flask import Flask, jsonify, request
from jsonschema import validate, ValidationError
from Model import Viagem

class api_viagem:
    def __init__(self) -> None:
        pass

    @staticmethod
    def registrar_viagem():
        modelo_viagem = {
            "type": "object",
            "properties": {
                "id_veiculo": {"type": "integer"},
                "data_viagem": {"type": "string", "format": "date"},
                "hora_viagem": {"type": "string", "format": "time"},
                "origem": {"type": "string"},
                "destino": {"type": "string"}
            },
            "required": [
                "id_veiculo",
                "data_viagem",
                "hora_viagem",
                "origem",
                "destino"
            ],
            "additionalProperties": False
        }

        # Dados recebidos da requisição
        data = request.json

        # Validação dos dados
        try:
            validate(instance=data, schema=modelo_viagem)
        except ValidationError as e:
            return jsonify({"erro": f"Erro de validação: {e.message}"}), 400

        # Criação da instância de Viagem
        try:
            viagem = Viagem(
                id_veiculo=data["id_veiculo"],
                data_viagem=data["data_viagem"],
                hora_viagem=data["hora_viagem"],
                origem=data["origem"],
                destino=data["destino"]
            )
            # Registrar no banco de dados
            viagem.cadastrar()
            return jsonify({"mensagem": "Viagem registrada com sucesso!", "dados": data}), 201
        except Exception as e:
            return jsonify({"erro": f"Erro ao registrar viagem: {str(e)}"}), 500
