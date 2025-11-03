package com.johnwilliam.ExpressoUnix.Configs.Authentication.Infra.Security;

import com.johnwilliam.ExpressoUnix.Configs.Authentication.Infra.Jwt.JwtAuthenticationFilter;
import com.johnwilliam.ExpressoUnix.Configs.Authentication.Repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfigs(
            UserRepository userRepository,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.userRepository = userRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception {
            httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/qr-code").permitAll()
                        .requestMatchers(HttpMethod.POST, "/qr-code/decode").permitAll()

                        .requestMatchers(HttpMethod.POST, "/assento").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.PUT, "/assento/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.DELETE, "/assento/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/assento").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/assento/**").hasAnyRole("ADMIN", "GESTAO")

                        .requestMatchers(HttpMethod.POST, "/funcionario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/funcionario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/funcionario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/funcionario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/funcionario/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/passageiro").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.PUT, "/passageiro/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.DELETE, "/passageiro/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/passageiro").hasAnyRole("ADMIN", "GESTAO", "MOTORISTA")
                        .requestMatchers(HttpMethod.GET, "/passageiro/**").hasAnyRole("ADMIN", "GESTAO", "MOTORISTA")

                        .requestMatchers(HttpMethod.POST, "/passagem").hasAnyRole("ADMIN", "GESTAO", "CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/passagem/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.PUT, "/passagem/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/passagem").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/passagem/**").hasAnyRole("ADMIN", "GESTAO", "MOTORISTA", "CLIENTE")

                        .requestMatchers(HttpMethod.POST, "/veiculo").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.DELETE, "/veiculo/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.PUT, "/veiculo/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/veiculo").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/veiculo/**").hasAnyRole("ADMIN", "GESTAO")

                        .requestMatchers(HttpMethod.POST, "/venda").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.PUT, "/venda/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.DELETE, "/venda/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/venda").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/venda/**").hasAnyRole("ADMIN", "GESTAO")

                        .requestMatchers(HttpMethod.POST, "/viagem").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.PUT, "/viagem/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.DELETE, "/viagem/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.DELETE, "/viagem/**").hasAnyRole("ADMIN", "GESTAO")
                        .requestMatchers(HttpMethod.GET, "/viagem/**").permitAll()

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
    }
}
