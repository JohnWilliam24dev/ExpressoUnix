import requests
from bs4 import BeautifulSoup
import re
origem="Salvador"
destino="Sao Paulo"
str_origem=str(origem)
str_destino=str(destino)
str_origem=str_origem.lower()
str_destino=str_destino.lower()
str_origem=str_origem.replace(" ","%20")
str_destino=str_destino.replace(" ","%20")


link=f"https://www.distanciasentrecidades.com/pesquisa?from={str_origem}%2C%20Brazil&to={str_destino}%2C%20Brazil"
response=requests.get(link)
print(response.status_code)
if(response.status_code!=200):
    print("Não foi possivel localizar.")
else:
    print("Requisição feita com sucesso!")
soup= BeautifulSoup(response.content,"html.parser")
search=str(soup.find(id="kmsruta"))
#search = soup.find('span', class_="distance__km")
distancia = search.replace("<strong id=\"kmsruta\">","")
distancia = distancia.replace("</strong>","")
distancia= float(distancia)
print(type(distancia))
print(distancia)