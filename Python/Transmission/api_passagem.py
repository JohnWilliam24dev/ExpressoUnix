from flask import Flask, jsonify, request
from jsonschema import validate, ValidationError
from Model import Passagem
class api_passagem:
    def __init__(self) -> None:
        pass

    def registrar_passagem():
        modelo_passagem = {
                "type": "object",
                "properties": {
                    "id_viagem": {"type": "integer"},
                    "id_assento": {"type": "integer"},
                    "id_passageiro": {"type": "integer"},
                    "data_passagem": {"type": "string", "format": "date"},
                    "hora_passagem": {"type": "string", "format": "time"},
                    "origem": {"type": "string"},
                    "destino": {"type": "string"},
                    "classe": {"type": "string"},
                    "status": {"type": "string"},
                    "preco": {"type": "number"}
                },
                "required": [
                    "id_viagem",
                    "id_assento",
                    "id_passageiro",
                    "data_passagem",
                    "hora_passagem",
                    "origem",
                    "destino",
                    "classe",
                    "status",
                    "preco"
                ],
                "additionalProperties": False
            }
        data = request.json

        
        try:
            validate(instance=data, schema=modelo_passagem)
        except ValidationError as e:
            return jsonify({"erro": f"Erro de validação: {e.message}"}), 400

        
        
    
        try:
            passagem = Passagem(
                id_viagem=data["id_viagem"],
                id_assento=data["id_assento"],
                id_passageiro=data["id_passageiro"],
                data=data["data_passagem"],
                hora=data["hora_passagem"],
                origem=data["origem"],
                destino=data["destino"],
                classe=data["classe"],
                status=data["status"],
                preco=None  # Calculado automaticamente no método `cadastrar`
            )
            # Registrar no banco de dados
            passagem.cadastrar()
            return jsonify({"mensagem": "Passagem registrada com sucesso!"}), 201
        except Exception as e:
            return jsonify({"erro": f"Erro ao registrar passagem: {str(e)}"}), 500