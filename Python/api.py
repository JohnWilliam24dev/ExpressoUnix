from fastapi import FastAPI, HTTPException
from fastapi.responses import JSONResponse
from datetime import datetime


from util import buscar_distancia, buscar_horario, calculadora_horario

app = FastAPI()


TAXA_POR_KM = 0.25


@app.get("/viagem")
def get_viagem(
        cidade_origem: str,
        cidade_destino: str,
        horario_partida: str  # formato esperado: "HH:MM:SS"
):
    try:
        # Obtem a distância (em km) entre as cidades
        distancia = buscar_distancia(cidade_origem, cidade_destino)
        if distancia == -1:
            raise HTTPException(status_code=404,
                                detail="Não foi possível obter a distância para as cidades informadas.")

        # Obtem a duração da viagem (a função pode retornar algo como "2 horas", "1 dia e 3 horas", etc.)
        duracao_viagem = buscar_horario(cidade_origem, cidade_destino)

        # Calcula o preço da passagem com base na distância (a regra pode ser ajustada conforme necessário)
        preco = round(distancia * TAXA_POR_KM, 2)

        # Calcula o horário de chegada a partir do horário de partida e duração da viagem
        horario_chegada = calculadora_horario(duracao_viagem, horario_partida)

        # Retorna os dados no formato JSON
        return JSONResponse(content={
            "cidade_origem": cidade_origem,
            "cidade_destino": cidade_destino,
            "horario_partida": horario_partida,
            "distancia_km": distancia,
            "duracao_viagem": duracao_viagem,
            "preco_passagem": preco,
            "horario_chegada": horario_chegada
        })
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
