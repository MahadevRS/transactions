package com.example.Transaction.models;

import com.example.Transaction.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;

@Entity
@Table
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private int amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    private int amountDeducted;

    @CreationTimestamp
    private LocalTime time;

    @ManyToOne
    @JoinColumn
    private User user;


}
