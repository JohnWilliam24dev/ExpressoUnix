from os import system as S
from Model import Transporte
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
    capacidade=input("Digite a capacidade: ")
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
    onibus= Transporte(classe,capacidade,status)
    onibus.cadastrar()
    loop=input("Deseja cadastrar mais um veiculo (S/N):")
    if loop=="S" or loop=="s":
        continuar=True
    else: 
        continuar=False
