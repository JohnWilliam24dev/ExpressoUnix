import mysql.connector
from .CrudInterface import CrudInterface
class Assento(CrudInterface):
    def __init__(self, id_viagem, numero_assento, status_assento="Livre"):
        self.id_viagem = id_viagem
        self.numero_assento = numero_assento
        self.status_assento = status_assento

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
            INSERT INTO assento (id_viagem, numero_assento, status_assento)
            VALUES (%s, %s, %s)
            """
            values = (self.id_viagem, self.numero_assento, self.status_assento)
            cursor.execute(query, values)
            connection.commit()
            print("Assento cadastrado com sucesso.")
        except mysql.connector.Error as err:
            print(f"Erro ao cadastrar assento: {err}")
        finally:
            cursor.close()
            connection.close()

    @staticmethod
    def buscar(id_assento):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "SELECT * FROM assento WHERE id_assento = %s"
            cursor.execute(query, (id_assento,))
            assento = cursor.fetchone()
            if assento:
                print("Assento encontrado:", assento)
                return assento
            else:
                print("Assento não encontrado.")
                return None
        finally:
            cursor.close()
            connection.close()

    def atualizar(self, id_assento):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            UPDATE assento
            SET id_viagem = %s, numero_assento = %s, status_assento = %s
            WHERE id_assento = %s
            """
            values = (self.id_viagem, self.numero_assento, self.status_assento, id_assento)
            cursor.execute(query, values)
            connection.commit()
            print("Assento atualizado com sucesso.")
        except mysql.connector.Error as err:
            print(f"Erro ao atualizar assento: {err}")
        finally:
            cursor.close()
            connection.close()

    @staticmethod
    def deletar(id_assento):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "DELETE FROM assento WHERE id_assento = %s"
            cursor.execute(query, (id_assento,))
            connection.commit()
            print("Assento deletado com sucesso.")
        except mysql.connector.Error as err:
            print(f"Erro ao deletar assento: {err}")
        finally:
            cursor.close()
            connection.close()