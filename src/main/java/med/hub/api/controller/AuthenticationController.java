package med.hub.api.controller;

import jakarta.validation.Valid;
import med.hub.api.infra.security.DataJWTToken;
import med.hub.api.infra.security.TokenService;
import med.hub.api.user.DataAuthenticationUser;
import med.hub.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity AuthenticateUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.login(),
                dataAuthenticationUser.password());
        var userAuthenticated = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(JWTtoken));
    }
}
