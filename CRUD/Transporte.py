import mysql.connector

class Transporte:
    def __init__(self, classe, capacidade, status):
        self.classe = classe
        self.capacidade = capacidade
        self.status = status

    def cadastrar_transporte(self):
        conexao = mysql.connector.connect(
            host="localhost",      
            user="root",    
            password="angelofthenight",  
            database="ExpressoUnix"
        )
        cursor = conexao.cursor()
        command = "INSERT INTO veiculo (classe, capacidade, status_veiculo) VALUES (%s, %s, %s)"
        valores = (self.classe, self.capacidade, self.status)
        
        # Grava o comando SQL em um arquivo para referência
        with open("insert.sql", "a") as arquivo:
            arquivo.write(f"INSERT INTO veiculo (classe, capacidade, status_veiculo) VALUES ({self.classe}, {self.capacidade}, {self.status});\n")

        try:
            cursor.execute(command, valores)
            conexao.commit()
            print(f"{cursor.rowcount} registro(s) inserido(s) com sucesso!")
        except mysql.connector.Error as err:
            print(f"Erro ao inserir dados: {err}")
        finally:
            cursor.close()
            conexao.close()
    
    @staticmethod
    def buscar_transporte(transporte_id):
        conexao = mysql.connector.connect(
            host="localhost",      
            user="root",    
            password="angelofthenight",  
            database="ExpressoUnix"
        )
        cursor = conexao.cursor()
        command = "SELECT * FROM veiculo WHERE id = %s"
        
        try:
            cursor.execute(command, (transporte_id,))
            transporte = cursor.fetchone()
            if transporte:
                print("Transporte encontrado:", transporte)
                return transporte
            else:
                print("Transporte não encontrado.")
                return None
        except mysql.connector.Error as err:
            print(f"Erro ao buscar dados: {err}")
        finally:
            cursor.close()
            conexao.close()

    def atualizar_transporte(self, transporte_id):
        conexao = mysql.connector.connect(
            host="localhost",      
            user="root",    
            password="angelofthenight",  
            database="ExpressoUnix"
        )
        cursor = conexao.cursor()
        command = """
            UPDATE veiculo
            SET classe = %s, capacidade = %s, status_veiculo = %s
            WHERE id = %s
        """
        valores = (self.classe, self.capacidade, self.status, transporte_id)

        try:
            cursor.execute(command, valores)
            conexao.commit()
            print(f"{cursor.rowcount} registro(s) atualizado(s) com sucesso!")
        except mysql.connector.Error as err:
            print(f"Erro ao atualizar dados: {err}")
        finally:
            cursor.close()
            conexao.close()
    
    @staticmethod
    def deletar_transporte(transporte_id):
        conexao = mysql.connector.connect(
            host="localhost",      
            user="root",    
            password="angelofthenight",  
            database="ExpressoUnix"
        )
        cursor = conexao.cursor()
        command = "DELETE FROM veiculo WHERE id = %s"

        try:
            cursor.execute(command, (transporte_id,))
            conexao.commit()
            print(f"{cursor.rowcount} registro(s) deletado(s) com sucesso!")
        except mysql.connector.Error as err:
            print(f"Erro ao deletar dados: {err}")
        finally:
            cursor.close()
            conexao.close()