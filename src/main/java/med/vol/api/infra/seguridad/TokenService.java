package med.vol.api.infra.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.vol.api.domain.usuarios.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.key}")
    private String apiKey;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiKey);
            String token = JWT.create()
                    .withIssuer("vol med")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00")))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }

    public String verifyToken(String token){
        DecodedJWT verifier=null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiKey);
            verifier = JWT.require(algorithm)
                    .withIssuer("vol med")
                    .build()
                    .verify(token);


            verifier.getSubject();
        } catch (JWTVerificationException exception){
            throw  new RuntimeException(exception.getMessage());
        }

        if (verifier.getSubject()== null){
            throw new RuntimeException("JWT no valido");
        }
        return verifier.getSubject();
    }
}
