import Fastify from "fastify";
import route from "./routes";
const fastify = Fastify({
    logger: true
  })
const start=async ()=>{


    await fastify.register(route)
    try {
        await fastify.listen({port:3300})
    } catch (err) {
        process.exit(1)
    }

}
start();