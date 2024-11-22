from Model import Transporte
from flask import Flask, jsonify, request
from jsonschema import validate, ValidationError
class api_transporte:
    def __init__(self) -> None:
        pass
    def registrar_transporte():
        model_tranporte={
            "type":"object",
            "properties":{
                "classe":{
                        "type":"string",
                        "enum":["Convencional","Executivo","Leito","Semi-Leito","Premium"]
                        },
                "capacidade":{"type":"integer"},
                "status":{
                        "type":"string",
                        "enum":["Disponivel","Indisponivel"]
                        }
            },
            "required": [
                    "classe",
                    "capacidade",
                    "status"
                ],
                "additionalProperties": False
        }
        data = request.json

        
        try:
            validate(instance=data, schema=model_tranporte)
        except ValidationError as e:
            return jsonify({"erro": f"Erro de validação: {e.message}"}), 400

        
        
        transporte = Transporte(
            classe=data["classe"],
            capacidade=data["capacidade"],
            status=data["status"]
        )

        
        try:
            transporte.cadastrar()
            return jsonify({"mensagem": "Transporte registrado com sucesso!", "dados": data}), 201
        except Exception as e:
            return jsonify({"erro": f"Erro ao registrar transporte: {str(e)}"}), 500
