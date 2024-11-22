import mysql.connector
from .CrudInterface import CrudInterface
import util

class Passagem(CrudInterface):
    def __init__(self, id_viagem, id_assento, id_passageiro, data, hora, origem, destino, classe, status,preco):
        self.id_viagem = id_viagem
        self.id_assento = id_assento
        self.id_passageiro = id_passageiro
        self.data_passagem = data
        self.hora_passagem = hora
        self.origem = origem
        self.destino = destino
        self.classe = classe
        self.status = status
        self.distancia = 0
        self.preco = preco

    def get_valor_total(self):
        def preco_classe(classe):
            if classe == "leito":
                return 80.00
            elif classe == "semi-leito":
                return 50.00
            elif classe == "executivo":
                return 40.00
            elif classe == "convencional":
                return 30.00
            elif classe == "premium":
                return 200
            else:
                return -1

        preco_fixo = preco_classe(self.classe)
        preco_variavel = self.distancia * 0.25
        valor_total = preco_fixo + preco_variavel
        return valor_total

    def cadastrar(self):
        self.distancia=util.buscar_distancia(self.origem,self.destino)
        self.preco=self.get_valor_total()
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            INSERT INTO passagem (status, id_viagem, id_assento, id_passageiro, data_passagem, hora_passagem, origem, destino, distancia, preco)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """
            values = (
                self.status, self.id_viagem, self.id_assento, self.id_passageiro,
                self.data_passagem, self.hora_passagem, self.origem, self.destino,
                self.distancia, self.preco
            )
            cursor.execute(query, values)
            connection.commit()
            print("Passagem cadastrada com sucesso.")
            with open("insert.sql", "a") as arquivo:
                
                arquivo.write(f"INSERT INTO passagem (status, id_viagem, id_assento, id_passageiro, data_passagem, hora_passagem, origem, destino, distancia, preco) VALUES ({self.status}, {self.id_viagem}, {self.id_assento}, {self.id_passageiro}, {self.data_passagem}, {self.hora_passagem}, {self.origem}, {self.destino}, {self.distancia}, {self.preco});\n")

        finally:
            cursor.close()
            connection.close()

    
    def buscar(self,passagem_id):
        connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="angelofthenight",
        database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor(dictionary=True)  # Retorna os resultados como dicionários
            query = "SELECT * FROM passagem WHERE id_passagem = %s"
            cursor.execute(query, (passagem_id,))
            passagem = cursor.fetchone()
            if passagem:
                print("Passagem encontrada:", passagem)
                # Retorna uma instância da classe com os atributos preenchidos
                
                self.id_viagem=passagem['id_viagem'],
                self.id_assento=passagem['id_assento'],
                self.id_passageiro=passagem['id_passageiro'],
                self.data=passagem['data_passagem'],
                self.hora=passagem['hora_passagem'],
                self.origem=passagem['origem'],
                self.destino=passagem['destino'],
                self.classe=passagem['classe'],  # Classe pode ser preenchida posteriormente, se necessário
                self.status=passagem['status']
                
            else:
                print("Passagem não encontrada.")
                return None
        finally:
            cursor.close()
            connection.close()

    def atualizar(self, passagem_id):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            UPDATE passagem
            SET status = %s, id_viagem = %s, id_assento = %s, id_passageiro = %s,
                data_passagem = %s, hora_passagem = %s, origem = %s, destino = %s,
                distancia = %s, preco = %s
            WHERE id_passagem = %s
            """
            values = (
                self.status, self.id_viagem, self.id_assento, self.id_passageiro,
                self.data_passagem, self.hora_passagem, self.origem, self.destino,
                self.distancia, self.preco, passagem_id
            )
            cursor.execute(query, values)
            connection.commit()
            print("Passagem atualizada com sucesso.")
        finally:
            cursor.close()
            connection.close()

    @staticmethod
    def deletar(passagem_id):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "DELETE FROM passagem WHERE id_passagem = %s"
            cursor.execute(query, (passagem_id,))
            connection.commit()
            print("Passagem deletada com sucesso.")
        finally:
            cursor.close()
            connection.close()