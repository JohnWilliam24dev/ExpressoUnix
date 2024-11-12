import mysql.connector
class Transporte:


    def __init__(self,classe,capacidade,status):
        self.classe=classe
        self.capacidade=capacidade
        self.status=status
        
    def cadastrar_transporte(self):
        conexao=mysql.connector.connect(
            host="localhost",      
            user="root",    
            password="angelofthenight",  
            database="ExpressoUnix"
        )
        cursor=conexao.cursor()

        command="INSERT INTO veiculo (classe, capacidade, status_veiculo) VALUES(%s,%s,%s)"
        valores=(self.classe,self.capacidade, self.status)
        arquivo=open("insert.sql","a")
        arquivo.write(f"INSERT INTO veiculo (classe, capacidade, status_veiculo) VALUES({self.classe},{self.capacidade},{self.status});\n")
        arquivo.close()


        try:
            cursor.execute(command, valores)  
            conexao.commit()              
            print(f"{cursor.rowcount} registro(s) inserido(s) com sucesso!")
        except mysql.connector.Error as err:
            print(f"Erro ao inserir dados: {err}")
        finally:
            cursor.close()    
            conexao.close()   
    def atualizar_status(self):
        return