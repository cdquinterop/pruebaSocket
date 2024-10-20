package com.socket.pruebaSocket.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;
    private String status;
    private String internalTransactionReference;
    private double amount = 0;
    private String transferNumber;
    private LocalDateTime date;
    private String currency = "USD";
    private String branchId;
    private String posId;
    private String description;
    private String ordererName;
    private String ordererIdentification;
}
