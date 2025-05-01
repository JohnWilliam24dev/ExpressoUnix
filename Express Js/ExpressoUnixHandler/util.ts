const axios = require('axios');
const cheerio = require('cheerio');

function extrairDias(str) {
  const match = str.match(/(\d+)\s*dia?/);
  return match ? parseInt(match[1], 10) : null;
}

function extrairHoras(str) {
  const match = str.match(/(\d+)\s*hora?/);
  return match ? parseInt(match[1], 10) : null;
}

function extrairMinutos(str) {
  const match = str.match(/(\d+)\s*min?/);
  return match ? parseInt(match[1], 10) : null;
}

function buscarDistancia(origem, destino) {
  const strOrigem = encodeURIComponent(origem.toLowerCase());
  const strDestino = encodeURIComponent(destino.toLowerCase());

  const link = `https://www.distanciasentrecidades.com/pesquisa?from=${strOrigem}%2C%20Brazil&to=${strDestino}%2C%20Brazil`;

  try {
    const response =  axios.get(link);
    const $ = cheerio.load(response.data);
    const distanciaRaw = $('#kmsruta').text().replace('.', '');
    return parseFloat(distanciaRaw);
  } catch (error) {
    console.log('Não foi possível localizar.');
    return -1;
  }
}

async function buscarHorario(origem, destino) {
  const strOrigem = encodeURIComponent(origem.toLowerCase());
  const strDestino = encodeURIComponent(destino.toLowerCase());

  const link = `https://www.distanciasentrecidades.com/pesquisa?from=${strOrigem}%2C%20Brazil&to=${strDestino}%2C%20Brazil`;

  try {
    const response = await axios.get(link);
    const $ = cheerio.load(response.data);
    const horarios = $('strong').toArray();
    const horaTexto = $(horarios[4]).text().replace('.', '');
    return horaTexto;
  } catch (error) {
    console.log('Não foi possível localizar.');
    return -1;
  }
}

function conversorDeHorario(horario) {
  if (horario.includes('dia')) {
    const dias = extrairDias(horario);
    const horas = extrairHoras(horario);
    return dias * 24 + horas;
  } else if (horario.includes('min')) {
    const horas = extrairHoras(horario) || 0;
    const minutos = extrairMinutos(horario) || 0;
    return `${String(horas).padStart(2, '0')}:${String(minutos).padStart(2, '0')}:00`;
  }
}

function calculadoraHorario(horario, horarioPartida) {
  const hora = extrairHoras(horario) || 0;
  let calc;

  if (horario.includes('dia')) {
    const dia = extrairDias(horario) || 0;
    calc = (dia * 24) + hora;
  } else {
    const minutos = extrairMinutos(horario) || 0;
    calc = hora + (minutos / 60);
  }

  const [h, m, s] = horarioPartida.split(':').map(Number);
  const partida = new Date();
  partida.setHours(h, m, s);

  const chegada = new Date(partida.getTime() + calc * 60 * 60 * 1000);
  return chegada.toTimeString().split(' ')[0];
}
console.log(buscarDistancia("São Paulo","Salvador"))