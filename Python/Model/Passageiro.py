from .CrudInterface import CrudInterface
import mysql.connector

class Passageiro(CrudInterface):
    def __init__(self, nome=None, email=None, telefone=None, cpf=None, data_nascimento=None):
        self.nome = nome
        self.email = email
        self.telefone = telefone
        self.cpf = cpf
        self.data_nascimento = data_nascimento

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
            INSERT INTO passageiro (nome, email, telefone, cpf, data_nascimento)
            VALUES (%s, %s, %s, %s, %s)
            """
            values = (self.nome, self.email, self.telefone, self.cpf, self.data_nascimento)
            cursor.execute(query, values)
            connection.commit()
            print("Passageiro cadastrado com sucesso.")
            with open("insert.sql", "a") as arquivo:
                 arquivo.write(f"INSERT INTO passageiro (nome, email, telefone, cpf, data_nascimento) VALUES ('{self.nome}', '{self.email}', '{self.telefone}', '{self.cpf}', '{self.data_nascimento}');\n")
        except mysql.connector.Error as err:
            print(f"Erro ao cadastrar passageiro: {err}")
        finally:
            cursor.close()
            connection.close()

    def buscar(self, id_passageiro):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor(dictionary=True)  # Retorna resultados como dicionários
            query = "SELECT * FROM passageiro WHERE id_passageiro = %s"
            cursor.execute(query, (id_passageiro,))
            passageiro = cursor.fetchone()
            if passageiro:
                print("Passageiro encontrado:", passageiro)
                # Preenchendo os atributos da instância
                self.nome = passageiro['nome']
                self.email = passageiro['email']
                self.telefone = passageiro['telefone']
                self.cpf = passageiro['cpf']
                self.data_nascimento = passageiro['data_nascimento']
                return self
            else:
                print("Passageiro não encontrado.")
                return None
        except mysql.connector.Error as err:
            print(f"Erro ao buscar passageiro: {err}")
        finally:
            cursor.close()
            connection.close()

    def atualizar(self, id_passageiro):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            UPDATE passageiro
            SET nome = %s, email = %s, telefone = %s, cpf = %s, data_nascimento = %s
            WHERE id_passageiro = %s
            """
            values = (self.nome, self.email, self.telefone, self.cpf, self.data_nascimento, id_passageiro)
            cursor.execute(query, values)
            connection.commit()
            print("Passageiro atualizado com sucesso.")
        except mysql.connector.Error as err:
            print(f"Erro ao atualizar passageiro: {err}")
        finally:
            cursor.close()
            connection.close()

    @staticmethod
    def deletar(id_passageiro):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "DELETE FROM passageiro WHERE id_passageiro = %s"
            cursor.execute(query, (id_passageiro,))
            connection.commit()
            print("Passageiro deletado com sucesso.")
        except mysql.connector.Error as err:
            print(f"Erro ao deletar passageiro: {err}")
        finally:
            cursor.close()
            connection.close()