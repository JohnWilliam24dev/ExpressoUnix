from os import system as S
import interfaces

def menu_principal():
    continuar = True
    while continuar:
        S("clear")
        print("Bem-vindo ao Sistema de Gestão Expresso Unix")
        print("Escolha uma opção para acessar o cadastro:")
        print("1) Cadastro de Transporte")
        print("2) Cadastro de Viagem")
        print("3) Cadastro de Passagem")
        print("4) Cadastro de Funcionário")
        print("5) Sair")
        
        try:
            opcao = int(input("--> "))
        except ValueError:
            print("Opção inválida! Por favor, insira um número.")
            input("Pressione Enter para continuar...")
            continue
        
        if opcao == 1:
            interfaces.interface_transporte()
        elif opcao == 2:
            interfaces.interface_viagem()
        elif opcao == 3:
            interfaces.interface_passagem()
        elif opcao == 4:
            interfaces.interface_funcionario()
        elif opcao == 5:
            continuar = False
            print("Saindo do sistema...")
        else:
            print("Opção inválida! Tente novamente.")
        
        if continuar:
            continuar = input("Deseja continuar as operações? (S/N): ").strip().lower() == "s"

menu_principal()