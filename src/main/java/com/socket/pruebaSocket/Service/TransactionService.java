package com.socket.pruebaSocket.Service;

import com.socket.pruebaSocket.Entity.Transaction;
import com.socket.pruebaSocket.Repository.transactionRepository;
import com.socket.pruebaSocket.Controllers.PaymentStatusWebSocketHandler; // Importar el handler
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private transactionRepository transactionRepository;

    @Autowired
    private PaymentStatusWebSocketHandler paymentStatusWebSocketHandler; // Inyectar el handler

    // Método para guardar una nueva transacción
    public Transaction saveTransaction(Transaction transaction) {
        // Generar automáticamente el UUID y la fecha actual
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setDate(LocalDateTime.now());

        // Guardar la transacción en la base de datos
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Enviar actualización a través del WebSocket
        paymentStatusWebSocketHandler.sendTransactionUpdate(savedTransaction.getTransactionId().toString(), savedTransaction.getStatus());

        return savedTransaction;
    }
}
