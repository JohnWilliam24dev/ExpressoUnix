import mysql.connector
from .CrudInterface import CrudInterface

class Viagem(CrudInterface):
    def __init__(self, id_veiculo, data_viagem, hora_viagem, origem, destino):
        self.id_veiculo = id_veiculo
        self.data_viagem = data_viagem
        self.hora_viagem = hora_viagem
        self.origem = origem
        self.destino = destino

    def cadastrar(self):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            INSERT INTO viagem (id_veiculo, data_viagem, hora_viagem, origem, destino)
            VALUES (%s, %s, %s, %s, %s)
            """
            values = (self.id_veiculo, self.data_viagem, self.hora_viagem, self.origem, self.destino)
            cursor.execute(query, values)
            connection.commit()
            print("Viagem cadastrada com sucesso.")
            with open("insert.sql", "a") as arquivo:
                arquivo.write(f"INSERT INTO viagem (id_veiculo, data_viagem, hora_viagem, origem, destino) VALUES ({self.id_veiculo}, '{self.data_viagem}', '{self.hora_viagem}', '{self.origem}', '{self.destino}');\n")

        finally:
            cursor.close()
            connection.close()

    
    def buscar(self,id_viagem):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "SELECT * FROM viagem WHERE id_viagem = %s"
            cursor.execute(query, (id_viagem,))
            viagem = cursor.fetchone()
            if viagem:
                print("Viagem encontrada:", viagem)
                
                # Retorna uma instância da classe com os atributos preenchidos
                
                self.id_viagem=viagem['id_veiculo'],
                
                self.data=viagem['data_viagem'],
                self.hora=viagem['hora_viagem'],
                self.origem=viagem['origem'],
                self.destino=viagem['destino'],
               
            else:
                print("Viagem não encontrada.")
                return None
        finally:
            cursor.close()
            connection.close()

    def atualizar(self, id_viagem):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            UPDATE viagem
            SET id_veiculo = %s, data_viagem = %s, hora_viagem = %s, origem = %s, destino = %s
            WHERE id_viagem = %s
            """
            values = (self.id_veiculo, self.data_viagem, self.hora_viagem, self.origem, self.destino, id_viagem)
            cursor.execute(query, values)
            connection.commit()
            print("Viagem atualizada com sucesso.")
        finally:
            cursor.close()
            connection.close()

    @staticmethod
    def deletar(id_viagem):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "DELETE FROM viagem WHERE id_viagem = %s"
            cursor.execute(query, (id_viagem,))
            connection.commit()
            print("Viagem deletada com sucesso.")
        finally:
            cursor.close()
            connection.close()