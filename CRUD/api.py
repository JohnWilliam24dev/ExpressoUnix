from flask import Flask, jsonify, request
from jsonschema import validate, ValidationError

from Transmission import *


app = Flask(__name__)

@app.route('/expresso_unix/hello', methods=['GET'])
def hello_world():
    
    return jsonify({'message': 'Olá, Mundo!'},
                   {'string':'API saindo quentin'}), 200, {'Content-Type': 'application/json; charset=utf-8'}

@app.route('')



@app.route('/expresso_unix/adm/registrar_passagem', methods=['POST'])
def post_passagem():
    return api_passagem.registrar_passagem()
@app.route('/expresso_unix/adm/registrar_viagem', methods=['POST'])
def post_passagem():
    return api_passagem.registrar_passagem()
@app.route('/expresso_unix/adm/registrar_funcionario', methods=['POST'])
def post_passagem():
    return api_passagem.registrar_passagem()
@app.route('/expresso_unix/adm/registrar_passageiro', methods=['POST'])
def post_passagem():
    return api_passageiro.registrar_passageiro()

@app.route('/expresso_unix/adm/registrar_transporte', methods=['POST'])
def post_transporte():
    return api_transporte.registrar_transporte()
@app.route('/expresso_unix/request_cpf',methods=['POST'])
def postcpf():
    try:
        # Obtém o JSON enviado na requisição
        data = request.get_json()
        
        # Verifica se a chave 'cpf' existe no JSON
        if 'cpf' not in data:
            return jsonify({"error": "CPF não fornecido"}), 400
        
        # Armazena o CPF em uma variável
        cpf = data['cpf']
        
        # Retorna o CPF recebido (opcional)
        return jsonify({"received_cpf": cpf}), 200
    
    except Exception as e:
        
        return jsonify({"error": str(e)}), 500

    


if __name__ == '__main__':
    app.run(debug=True)