package com.product.managing.system.config;

import com.product.managing.system.rest.execptions.EntryPontAccessHandler;
import com.product.managing.system.rest.execptions.RoleAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    private final RsaKeysConfig rsaKeysConfig;
    private final RoleAccessDeniedHandler accessDeniedHandler;
    private final EntryPontAccessHandler entryPontAccessHandler;

    public SecurityConfig(RsaKeysConfig rsaKeysConfig, RoleAccessDeniedHandler roleAccessDeniedHandler, EntryPontAccessHandler entryPontAccessHandler) {
        this.rsaKeysConfig = rsaKeysConfig;
        this.accessDeniedHandler = roleAccessDeniedHandler;
        this.entryPontAccessHandler = entryPontAccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(entryPontAccessHandler))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeysConfig.publicKey()).build();
    }
}
