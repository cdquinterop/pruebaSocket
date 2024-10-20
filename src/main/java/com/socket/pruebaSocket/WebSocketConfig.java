package com.socket.pruebaSocket;

import com.socket.pruebaSocket.Controllers.PaymentStatusWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private PaymentStatusWebSocketHandler paymentStatusWebSocketHandler; // Inyectar el handler aquí

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Registra el handler en el endpoint "/ws/payment-status"
        registry.addHandler(paymentStatusWebSocketHandler, "/ws/payment-status")
                .setAllowedOrigins("*"); // Permite todos los orígenes
    }
}
