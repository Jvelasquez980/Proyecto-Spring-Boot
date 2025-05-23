package com.topicos.proyectospring.models;

import jakarta.persistence.*;

@Entity
@Table(name = "methods")
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownersName;
    private String cardNumber;
    private String cvv;
    private String expirationDate;

    private String year;
    private String month;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) // Llave foránea a la tabla clients
    private Client client;

    public PayMethod() {
    }

    public PayMethod(String ownersName, String cardNumber, String cvv, String month, String year, Client client) {
        this.ownersName = ownersName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = month + "/" + year;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) { 
        this.year = year; 
        updateExpirationDate();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) { 
        this.month = month; 
        updateExpirationDate();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    private void updateExpirationDate() {
        if (this.month != null && this.year != null) {
            this.expirationDate = this.month + "/" + this.year;
        }
    }
}
