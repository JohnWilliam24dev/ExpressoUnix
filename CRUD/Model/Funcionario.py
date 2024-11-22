import mysql.connector
from .CrudInterface import CrudInterface
class Funcionario(CrudInterface):
    def __init__(self, nome, data_nascimento, cargo):
        self.nome = nome
        self.data_nascimento = data_nascimento
        self.cargo = cargo

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
            INSERT INTO funcionario (nome, data_nascimento, cargo)
            VALUES (%s, %s, %s)
            """
            values = (self.nome, self.data_nascimento, self.cargo)
            cursor.execute(query, values)
            connection.commit()
            print("Funcionário cadastrado com sucesso.")
            with open("insert.sql", "a") as arquivo:
                 arquivo.write(f"INSERT INTO funcionario (nome, data_nascimento, cargo) VALUES ('{self.nome}', '{self.data_nascimento}', '{self.cargo}');\n")
        except mysql.connector.Error as err:
            print(f"Erro ao cadastrar funcionário: {err}")
        finally:
            cursor.close()
            connection.close()

    
    def buscar(self,id_funcionario):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "SELECT * FROM funcionario WHERE id_funcionario = %s"
            cursor.execute(query, (id_funcionario,))
            funcionario = cursor.fetchone()
            if funcionario:
                print("Funcionário encontrado:", funcionario)
                self.nome = funcionario['nome']
                self.email = funcionario['email']
                self.telefone = funcionario['telefone']
                self.cpf = funcionario['cpf']
                self.data_nascimento = funcionario['data_nascimento']
                return self
                
            else:
                print("Funcionário não encontrado.")
                return None
        except mysql.connector.Error as err:
            print(f"Erro ao buscar funcionário: {err}")
        finally:
            cursor.close()
            connection.close()

    def atualizar(self, id_funcionario):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = """
            UPDATE funcionario
            SET nome = %s, data_nascimento = %s, cargo = %s
            WHERE id_funcionario = %s
            """
            values = (self.nome, self.data_nascimento, self.cargo, id_funcionario)
            cursor.execute(query, values)
            connection.commit()
            print("Funcionário atualizado com sucesso.")
        except mysql.connector.Error as err:
            print(f"Erro ao atualizar funcionário: {err}")
        finally:
            cursor.close()
            connection.close()

    @staticmethod
    def deletar(id_funcionario):
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="angelofthenight",
            database="ExpressoUnix"
        )
        try:
            cursor = connection.cursor()
            query = "DELETE FROM funcionario WHERE id_funcionario = %s"
            cursor.execute(query, (id_funcionario,))
            connection.commit()
            print("Funcionário deletado com sucesso.")
        except mysql.connector.Error as err:
            print(f"Erro ao deletar funcionário: {err}")
        finally:
            cursor.close()
            connection.close()