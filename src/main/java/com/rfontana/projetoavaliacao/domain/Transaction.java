package com.rfontana.projetoavaliacao.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private Long customerId;

    private String paymentMethod;

    private String status;

    private String maskedCardNumber;

    @Column(length = 3)
    private String currencyCode;

    private BigDecimal amount;

    private Date date;

    @JsonIgnore
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
    private Boolean deleted;


    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public void setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getTransactionId(), that.getTransactionId()) &&
                Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getPaymentMethod(), that.getPaymentMethod()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getMaskedCardNumber(), that.getMaskedCardNumber()) &&
                Objects.equals(getCurrencyCode(), that.getCurrencyCode()) &&
                Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getUpdatedAt(), that.getUpdatedAt()) &&
                Objects.equals(getDeleted(), that.getDeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionId(), getCustomerId(), getPaymentMethod(), getStatus(), getMaskedCardNumber(), getCurrencyCode(), getAmount(), getDate(), getUpdatedAt(), getDeleted());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", customerId=" + customerId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", maskedCardNumber='" + maskedCardNumber + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", updatedAt=" + updatedAt +
                ", deleted=" + deleted +
                '}';
    }
}
