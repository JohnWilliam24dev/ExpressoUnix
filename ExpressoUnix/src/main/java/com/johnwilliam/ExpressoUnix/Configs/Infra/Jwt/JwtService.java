package com.johnwilliam.ExpressoUnix.Configs.Infra.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.johnwilliam.ExpressoUnix.Configs.DTO.UserDTO;
import com.johnwilliam.ExpressoUnix.Configs.Models.User;
import  org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
   @Value("${jwt.secret}")
   private String secret;

   @Value("${jwt.expiration}")
   private long expiration;

   public String generateToken(User user) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                   .withIssuer("expresso-unix")
                   .withIssuedAt(new Date())
                   .withSubject(user.getUsername())
                   .withClaim("role", user.getRole().roleToString())
                   .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                   .sign(algorithm);

       } catch(JWTCreationException exception) {
           throw new RuntimeException("Error while generating token", exception);
       }
   }

   public boolean isTokenValid(String token) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           JWT.require(algorithm)
                   .withIssuer("expresso-unix")
                   .build()
                   .verify(token);
           return true;
       }
       catch(Exception exception) {
           return false;
       }
   }

   public String extractLogin(String token) {
       Algorithm algorithm = Algorithm.HMAC256(secret);
       DecodedJWT decodedJWT = JWT.require(algorithm)
               .withIssuer("expresso-unix")
               .build()
               .verify(token);
       return decodedJWT.getSubject();
   }

   public String extractRole(String token) {
       Algorithm algorithm = Algorithm.HMAC256(secret);
       DecodedJWT decodedJWT = JWT.require(algorithm)
               .withIssuer("expresso-unix")
               .build()
               .verify(token);
       return decodedJWT.getClaim("role").asString();
   }
}
