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

    public PayMethod() {}

    public PayMethod(String ownersName, String cardNumber, String cvv, String month, String year) {
        this.ownersName = ownersName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = month + "/" + year;
    }

    public Long getId() { return id; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getOwnersName() { return ownersName; }
    public void setOwnersName(String ownersName) { this.ownersName = ownersName; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
}
