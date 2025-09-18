import { FastifyPluginAsync } from 'fastify';
import { travelInfo } from './services/routeService';
import { travelInfoB } from './services/routeBService';

type RouteQuery = {
  origin: string;
  destination: string;
};

const route: FastifyPluginAsync = async (server) => {
  server.get<{ Querystring: RouteQuery }>('/route', {
    schema: {
      querystring: {
        type: 'object',
        properties: {
          origin:      { type: 'string' },
          destination: { type: 'string' }
        },
        required: ['origin', 'destination']
      }
    }
  }, async (request, reply) => {
    const { origin, destination } = request.query;

    try {
  const info = await travelInfo(origin, destination);
  return info; 
} catch (err) {
  server.log.error("Erro no travelInfo, tentando travelInfoB...", err);
  try {
    const infoB = await travelInfoB(origin, destination);
    return infoB;
  } catch (errB) {
    server.log.error("Erro também no travelInfoB", errB);
    reply.status(500).send({ error: 'Erro ao calcular rota' });
  }
}
  });
};

export default route;