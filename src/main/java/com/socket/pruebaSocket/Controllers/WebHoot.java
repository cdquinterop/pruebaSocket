package com.socket.pruebaSocket.Controllers;

import com.socket.pruebaSocket.Entity.Transaction;
import com.socket.pruebaSocket.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class WebHoot {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PaymentStatusWebSocketHandler webSocketController;

    // Endpoint para recibir los datos del webhook
    @PostMapping("/webhook")
    public Transaction receiveTransaction(@RequestBody Transaction transaction) {
        // Guardar la transacción
        Transaction savedTransaction = transactionService.saveTransaction(transaction);


        return savedTransaction;
    }
}
