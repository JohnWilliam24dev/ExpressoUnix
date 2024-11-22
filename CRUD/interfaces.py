from os import system as S
import subprocess
import json
def interface_transporte():
    continuar= True
    while continuar ==True:

        S("clear")
        print("Escolha a classe de onibus a ser cadastrado")
        print("1) Convencional")
        print("2) Executivo")
        print("3) Semi-Leito")
        print("4) Leito")
        print("5) Premium")
        input_classe=int(input("-->"))
        capacidade=int(input("Digite a capacidade: "))
        S("clear")
        print("1) Disponivel")
        print("2) Indisponivel")
        input_status=int(input("Digite o Status: "))
        def switch_status(input):
            if input==1:
                return "Disponivel"
            elif input==2:
                return "Indisponivel"
        def switch_case_classe(input_classe): 

            match input_classe: 

                case 1: 

                    return "Convencional" 

                case 2: 

                    return "Executivo" 

                case 3: 

                    return "Semi-Leito" 
                
                case 4: 

                    return "Leito"
                
                case 5: 

                    return "Premium"

                case _: 

                    return "0" 
        status= switch_status(input_status)
        classe=switch_case_classe(input_classe)
        def post(classe,capacidade,status):
            url = "http://localhost:5000/expresso_unix/adm/registrar_transporte"
            dados = {
                "classe": classe,
                "capacidade": capacidade,
                "status": status
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
        post(classe,capacidade,status)
        loop=input("Deseja cadastrar mais um veiculo (S/N):")
        if loop=="S" or loop=="s":
            continuar=True
        else: 
            continuar=False


def interface_passageiro():
    continuar = True
    while continuar:
        S("clear")
        print("Cadastro de Passageiros")
        nome = input("Digite o nome do passageiro: ")
        email = input("Digite o e-mail do passageiro: ")
        telefone = input("Digite o telefone do passageiro: ")
        cpf = input("Digite o CPF do passageiro: ")
        data_nascimento = input("Digite a data de nascimento (AAAA-MM-DD): ")

        def post_passageiro(nome, email, telefone, cpf, data_nascimento):
            url = "http://localhost:5000/expresso_unix/adm/registrar_passageiro"
            dados = {
                "nome": nome,
                "email": email,
                "telefone": telefone,
                "cpf": cpf,
                "data_nascimento": data_nascimento
            }

            dados_json = json.dumps(dados)

            comando = [
                "curl", "-X", "POST", url,
                "-H", "Content-Type: application/json",
                "-d", dados_json
            ]

            resultado = subprocess.run(comando, capture_output=True, text=True)

            print("Status Code:", resultado.returncode)
            print("Resposta do Servidor:", resultado.stdout)

        post_passageiro(nome, email, telefone, cpf, data_nascimento)

        loop = input("Deseja cadastrar mais um passageiro (S/N): ")
        continuar = loop.lower() == "s"



def interface_viagem():
    continuar = True
    while continuar:
        S("clear")
        print("Cadastro de Viagens")
        id_veiculo = int(input("Digite o ID do veículo: "))
        data_viagem = input("Digite a data da viagem (AAAA-MM-DD): ")
        hora_viagem = input("Digite a hora da viagem (HH:MM:SS): ")
        origem = input("Digite a origem: ")
        destino = input("Digite o destino: ")

        def post_viagem(id_veiculo, data_viagem, hora_viagem, origem, destino):
            url = "http://localhost:5000/expresso_unix/adm/registrar_viagem"
            dados = {
                "id_veiculo": id_veiculo,
                "data_viagem": data_viagem,
                "hora_viagem": hora_viagem,
                "origem": origem,
                "destino": destino
            }

            dados_json = json.dumps(dados)

            comando = [
                "curl", "-X", "POST", url,
                "-H", "Content-Type: application/json",
                "-d", dados_json
            ]

            resultado = subprocess.run(comando, capture_output=True, text=True)

            print("Status Code:", resultado.returncode)
            print("Resposta do Servidor:", resultado.stdout)

        post_viagem(id_veiculo, data_viagem, hora_viagem, origem, destino)

        loop = input("Deseja cadastrar mais uma viagem (S/N): ")
        continuar = loop.lower() == "s"



def interface_passagem():
    continuar = True
    while continuar:
        S("clear")
        print("Cadastro de Passagens")
        id_viagem = int(input("Digite o ID da viagem: "))
        id_assento = int(input("Digite o ID do assento: "))
        id_passageiro = int(input("Digite o ID do passageiro: "))
        data_passagem = input("Digite a data da passagem (AAAA-MM-DD): ")
        hora_passagem = input("Digite a hora da passagem (HH:MM:SS): ")
        origem = input("Digite a origem: ")
        destino = input("Digite o destino: ")
        classe = input("Digite a classe (Ex: Econômica, Executiva): ")
        status = input("Digite o status (Ex: Confirmada, Cancelada): ")
        preco = float(input("Digite o preço da passagem: "))

        def post_passagem(id_viagem, id_assento, id_passageiro, data_passagem, hora_passagem, origem, destino, classe, status, preco):
            url = "http://localhost:5000/expresso_unix/adm/registrar_passagem"
            dados = {
                "id_viagem": id_viagem,
                "id_assento": id_assento,
                "id_passageiro": id_passageiro,
                "data_passagem": data_passagem,
                "hora_passagem": hora_passagem,
                "origem": origem,
                "destino": destino,
                "classe": classe,
                "status": status,
                "preco": preco
            }

            dados_json = json.dumps(dados)

            comando = [
                "curl", "-X", "POST", url,
                "-H", "Content-Type: application/json",
                "-d", dados_json
            ]

            resultado = subprocess.run(comando, capture_output=True, text=True)

            print("Status Code:", resultado.returncode)
            print("Resposta do Servidor:", resultado.stdout)

        post_passagem(id_viagem, id_assento, id_passageiro, data_passagem, hora_passagem, origem, destino, classe, status, preco)

        loop = input("Deseja cadastrar mais uma passagem (S/N): ")
        continuar = loop.lower() == "s"




def interface_funcionario():
    continuar = True
    while continuar:
        S("clear")
        print("Cadastro de Funcionários")
        nome = input("Digite o nome do funcionário: ")
        data_nascimento = input("Digite a data de nascimento do funcionário (AAAA-MM-DD): ")
        cargo = input("Digite o cargo do funcionário: ")

        def post_funcionario(nome, data_nascimento, cargo):
            url = "http://localhost:5000/expresso_unix/adm/registrar_funcionario"
            dados = {
                "nome": nome,
                "data_nascimento": data_nascimento,
                "cargo": cargo
            }

            dados_json = json.dumps(dados)

            comando = [
                "curl", "-X", "POST", url,
                "-H", "Content-Type: application/json",
                "-d", dados_json
            ]

            resultado = subprocess.run(comando, capture_output=True, text=True)

            print("Status Code:", resultado.returncode)
            print("Resposta do Servidor:", resultado.stdout)

        post_funcionario(nome, data_nascimento, cargo)

        loop = input("Deseja cadastrar mais um funcionário (S/N): ")
        continuar = loop.lower() == "s"




