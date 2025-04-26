package com.product.managing.system.rest.execptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.managing.system.rest.response.AuthentificationResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EntryPontAccessHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        var error = new AuthentificationResponse(
                "UNAUTHORIZED",
                "Authentication required to access to this resource"

        );
        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}
