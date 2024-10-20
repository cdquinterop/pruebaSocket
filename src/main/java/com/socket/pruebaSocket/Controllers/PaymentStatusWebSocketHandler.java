package com.socket.pruebaSocket.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentStatusWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(PaymentStatusWebSocketHandler.class);

    // Lista para guardar sesiones si es necesario
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // Agregar sesión a la lista
        logger.info("Conexión establecida: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Mensaje recibido: " + message.getPayload());
        // Aquí puedes manejar el mensaje recibido del cliente si es necesario
    }

    public void sendTransactionUpdate(String transactionId, String status) {
        // Este método se puede llamar para enviar actualizaciones de transacción a todos los clientes
        try {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage("Transaction ID: " + transactionId + ", Status: " + status));
                }
            }
        } catch (Exception e) {
            logger.error("Error al enviar mensaje: ", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // Remover sesión de la lista
        logger.info("Sesión cerrada: " + session.getId());
    }
}
