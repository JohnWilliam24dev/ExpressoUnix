import mysql.connector
import util

class Passagem:
    def __init__(self, viagem, assento, passageiro, data, hora, origem, destino, classe) -> None:
        self.viagem = viagem
        self.assento = assento
        self.passageiro = passageiro
        self.data = data
        self.hora = hora
        self.origem = origem
        self.destino = destino
        self.classe = classe
        self.distancia = util.buscar_distancia(self.origem, self.destino)
        self.preco = self.get_valor_total()
    
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
    
    def cadastrar_passagem(self):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            INSERT INTO passagens (viagem, assento, passageiro, data, hora, origem, destino, classe, preco)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
            """
            values = (self.viagem, self.assento, self.passageiro, self.data, self.hora, self.origem, self.destino, self.classe, self.preco)
            cursor.execute(query, values)
            connection.commit()
            print("Passagem cadastrada com sucesso.")
        finally:
            cursor.close()
            connection.close()
    
    @staticmethod
    def buscar_passagem(passagem_id):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "SELECT * FROM passagens WHERE id = %s"
            cursor.execute(query, (passagem_id,))
            passagem = cursor.fetchone()
            if passagem:
                print("Passagem encontrada:", passagem)
                return passagem
            else:
                print("Passagem não encontrada.")
                return None
        finally:
            cursor.close()
            connection.close()
    
    def atualizar_passagem(self, passagem_id):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            UPDATE passagens
            SET viagem = %s, assento = %s, passageiro = %s, data = %s, hora = %s, origem = %s, destino = %s, classe = %s, preco = %s
            WHERE id = %s
            """
            values = (self.viagem, self.assento, self.passageiro, self.data, self.hora, self.origem, self.destino, self.classe, self.preco, passagem_id)
            cursor.execute(query, values)
            connection.commit()
            print("Passagem atualizada com sucesso.")
        finally:
            cursor.close()
            connection.close()
    
    @staticmethod
    def deletar_passagem(passagem_id):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "DELETE FROM passagens WHERE id = %s"
            cursor.execute(query, (passagem_id,))
            connection.commit()
            print("Passagem deletada com sucesso.")
        finally:
            cursor.close()
            connection.close()