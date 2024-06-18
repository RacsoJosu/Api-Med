package med.vol.api.domain.usuarios;

import jakarta.validation.Valid;
import med.vol.api.domain.usuarios.dto.Login;
import med.vol.api.domain.usuarios.dto.LoginResponse;
import med.vol.api.domain.usuarios.entity.Usuario;
import med.vol.api.infra.seguridad.TokenService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid Login login){
        var token = new UsernamePasswordAuthenticationToken(login.username(), login.clave() ) ;




        var usuarioAutenticado = auth.authenticate(token).getPrincipal();

        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado);

        return ResponseEntity.ok(new LoginResponse(jwtToken));

    }
}
