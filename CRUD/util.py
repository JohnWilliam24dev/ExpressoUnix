import requests
from bs4 import BeautifulSoup
import re
from datetime import datetime, timedelta
def extrair_dias(string):
    match = re.search(r'(\d+)\s*dia?', string)
    return int(match.group(1)) if match else None

def extrair_horas(string):
    match = re.search(r'(\d+)\s*hora?', string)
    return int(match.group(1)) if match else None
def extrair_minutos(string):
    match = re.search(r'(\d+)\s*min?', string)
    return int(match.group(1)) if match else None
def buscar_distancia(origem:str,destino:str):
        str_origem=str(origem)
        str_destino=str(destino)
        str_origem=str_origem.lower()
        str_destino=str_destino.lower()
        str_origem=str_origem.replace(" ","%20")
        str_destino=str_destino.replace(" ","%20")


        link=f"https://www.distanciasentrecidades.com/pesquisa?from={str_origem}%2C%20Brazil&to={str_destino}%2C%20Brazil"
        response=requests.get(link)
        
        if(response.status_code!=200):
            print("Não foi possivel localizar.")
            return-1
        else:
            
            soup= BeautifulSoup(response.content,"html.parser")
            search=str(soup.find(id="kmsruta"))
            distancia = search.replace("<strong id=\"kmsruta\">","")
            distancia = distancia.replace("</strong>","")
            distancia = distancia.replace(".","")
            distancia= float(distancia)
        return distancia


def buscar_horario(origem:str,destino:str):
        str_origem=str(origem)
        str_destino=str(destino)
        str_origem=str_origem.lower()
        str_destino=str_destino.lower()
        str_origem=str_origem.replace(" ","%20")
        str_destino=str_destino.replace(" ","%20")


        link=f"https://www.distanciasentrecidades.com/pesquisa?from={str_origem}%2C%20Brazil&to={str_destino}%2C%20Brazil"
        response=requests.get(link)
        
        if(response.status_code!=200):
            print("Não foi possivel localizar.")
            return-1
        else:
            
            soup= BeautifulSoup(response.content,"html.parser")
            search=soup.findAll("strong")
            hour=str(search[4])
            hour = hour.replace("<strong>","")
            hour = hour.replace("</strong>","")
            hour = hour.replace(".","")
            
        return hour
print(buscar_distancia("campinas sp","Santos sp"))
horario=buscar_horario("campinas sp","santos sp")
print(horario)

def conversor_de_horario(horario: str)->str:
    
    if "dia" in horario:
        dias = extrair_dias(horario)
        horas = extrair_horas(horario)
        cont=horas+(dias*24)
        return cont
    elif "min" in horario:
        horas=extrair_horas(horario)
        minutos=extrair_minutos(horario)
        form_horas=(f"{horas:02}")
        form_minutos=(f"{minutos:02}")
        time=str(form_horas+":"+form_minutos+":00")
        time=time.replace(" ","")
        return time        

print(conversor_de_horario(horario))
def calculadora_horario(horario:str,horario_partida:str)->str:
    
    hora=int(extrair_horas(horario))
   
    if "dia"in horario:
        dia=int(extrair_dias(horario))
        calc=calc_dia=(dia*24)+hora
        return calc_dia
    else:
        minutos=int(extrair_minutos(horario))
        calc_hora=float((minutos/60)+hora)
        calc= calc_hora
    hora_partida = datetime.strptime(horario_partida, "%H:%M:%S")

    # Tempo de viagem em horas decimais (por exemplo, 1.3833 horas)
    tempo_viagem = calc

    # Convertendo o tempo de viagem para um timedelta em minutos
    minutos_viagem = tempo_viagem * 60
    tempo_viagem_delta = timedelta(minutes=minutos_viagem)

    # Calculando o horário de chegada
    hora_chegada = hora_partida + tempo_viagem_delta

    
    return hora_chegada.strftime("%H:%M:%S")
print(calculadora_horario(horario,"09:00:00"))

