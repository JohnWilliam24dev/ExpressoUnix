
import util
class Viagem:
    def __init__(self,veiculo,data,hora,origem,destino):
        self.veiculo=veiculo
        self.data=data
        self.hora=hora
        self.origem=origem
        self.destino=destino
        
        
    
    
    
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
        preco_fixo=preco_classe(self.veiculo)
        preco_variavel=self.distancia *0,25
        valor_total=preco_fixo+preco_variavel
        return valor_total
    
        
        
    
        