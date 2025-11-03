package com.johnwilliam.ExpressoUnix.Controllers;

import com.johnwilliam.ExpressoUnix.Configs.Authentication.DTO.UserDTO;
import com.johnwilliam.ExpressoUnix.Configs.Authentication.Infra.Jwt.JwtService;
import com.johnwilliam.ExpressoUnix.Configs.Authentication.Models.User;
import com.johnwilliam.ExpressoUnix.Configs.Authentication.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO dto) {
        userService.saveUser(dto);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
      try {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
          );
          String token = jwtService.generateToken(user);
          return ResponseEntity.ok(token);
      } catch(AuthenticationException exception) {
          return ResponseEntity.status(401).body("Invalid Credentials!");
      }

    }
}
