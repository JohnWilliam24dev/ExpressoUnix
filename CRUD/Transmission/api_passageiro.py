from flask import Flask, jsonify, request
from jsonschema import validate, ValidationError
from Model import Passageiro

class api_passageiro:
    def __init__(self) -> None:
        pass

    
    def registrar_passageiro():
        modelo_passageiro = {
            "type": "object",
            "properties": {
                "nome": {"type": "string"},
                "email": {"type": "string", "format": "email"},
                "telefone": {"type": "string", "pattern": r"^\d{10,15}$"},
                "cpf": {"type": "string", "pattern": r"^\d{11}$"},
                "data_nascimento": {"type": "string", "format": "date"}
            },
            "required": [
                "nome",
                "email",
                "telefone",
                "cpf",
                "data_nascimento"
            ],
            "additionalProperties": False
        }

        # Dados recebidos da requisição
        data = request.json

        # Validação dos dados
        try:
            validate(instance=data, schema=modelo_passageiro)
        except ValidationError as e:
            return jsonify({"erro": f"Erro de validação: {e.message}"}), 400

        # Criação da instância de Passageiro
        try:
            passageiro = Passageiro(
                nome=data["nome"],
                email=data["email"],
                telefone=data["telefone"],
                cpf=data["cpf"],
                data_nascimento=data["data_nascimento"]
            )
            # Registrar no banco de dados
            passageiro.cadastrar()
            return jsonify({"mensagem": "Passageiro registrado com sucesso!", "dados": data}), 201
        except Exception as e:
            return jsonify({"erro": f"Erro ao registrar passageiro: {str(e)}"}), 500
