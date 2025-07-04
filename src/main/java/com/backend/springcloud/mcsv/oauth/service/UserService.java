package com.backend.springcloud.mcsv.oauth.service;

import com.backend.springcloud.mcsv.oauth.model.User;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

    @Autowired
    //private WebClient.Builder webClientBuilder;
    private WebClient webClient;
    @Autowired
    private Tracer tracer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("llamada al metodo del service UserService::loadUserByUsername {}", username);
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        try {
            //User user = webClientBuilder.build().get().uri("/users/find/username/{username}", params)
            User user = webClient.get().uri("/users/find/username/{username}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();

            List<GrantedAuthority> roles = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            logger.info("Se ha iniciado session con exito con el username: {} ", user);
            tracer.currentSpan().tag("success login with user.username: ", user.getUsername());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnable(),
                    true, true, true, roles);

        } catch (WebClientException e) {
            //"User not found - error on login'" + username + "'No existe en el sistema"
            String errorMessage = String.format("Error al buscar el usuario %s: %s", username, e.getMessage());
            logger.error(errorMessage);
            tracer.currentSpan().tag("error.message", errorMessage + " : " + e.getMessage());
            throw new UsernameNotFoundException(errorMessage);
        }
    }
}
