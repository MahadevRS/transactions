package com.example.Transaction.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToOne
    @JoinColumn
    private Transaction transaction;
}
