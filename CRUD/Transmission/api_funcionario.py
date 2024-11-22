from flask import Flask, jsonify, request
from jsonschema import validate, ValidationError
from Model import Funcionario

class api_funcionario:
    def __init__(self) -> None:
        pass

    @staticmethod
    def registrar_funcionario():
        modelo_funcionario = {
            "type": "object",
            "properties": {
                "nome": {"type": "string"},
                "data_nascimento": {"type": "string", "format": "date"},
                "cargo": {"type": "string"}
            },
            "required": ["nome", "data_nascimento", "cargo"],
            "additionalProperties": False
        }

        
        data = request.json

        
        try:
            validate(instance=data, schema=modelo_funcionario)
        except ValidationError as e:
            return jsonify({"erro": f"Erro de validação: {e.message}"}), 400

        
        try:
            funcionario = Funcionario(
                nome=data["nome"],
                data_nascimento=data["data_nascimento"],
                cargo=data["cargo"]
            )
            
            funcionario.cadastrar()
            return jsonify({"mensagem": "Funcionário registrado com sucesso!", "dados": data}), 201
        except Exception as e:
            return jsonify({"erro": f"Erro ao registrar funcionário: {str(e)}"}), 500
