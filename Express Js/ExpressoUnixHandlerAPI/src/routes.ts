import { FastifyPluginAsync } from 'fastify';
import { travelInfo } from './services/routeService';

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
      server.log.error(err);
      reply.status(500).send({ error: 'Erro ao calcular rota' });
    }
  });
};

export default route;