package com.rfontana.projetoavaliacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "INVOICE")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceId;

    private BigDecimal total;

    private BigDecimal amountPaid;

    private Date date;

    private String status;

    @JsonIgnore
    private Boolean deleted;

    @Column(length = 3)
    private String currencyCode;

    private Integer tax;

    @OneToMany(cascade = {CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "INVOICE_ID")
    private List<Transaction> transactions;


    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(getInvoiceId(), invoice.getInvoiceId()) &&
                Objects.equals(getTotal(), invoice.getTotal()) &&
                Objects.equals(getAmountPaid(), invoice.getAmountPaid()) &&
                Objects.equals(getDate(), invoice.getDate()) &&
                Objects.equals(getStatus(), invoice.getStatus()) &&
                Objects.equals(getDeleted(), invoice.getDeleted()) &&
                Objects.equals(getCurrencyCode(), invoice.getCurrencyCode()) &&
                Objects.equals(getTax(), invoice.getTax()) &&
                Objects.equals(getTransactions(), invoice.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getTotal(), getAmountPaid(), getDate(), getStatus(), getDeleted(), getCurrencyCode(), getTax(), getTransactions());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", total=" + total +
                ", amountPaid=" + amountPaid +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", deleted=" + deleted +
                ", currencyCode='" + currencyCode + '\'' +
                ", tax=" + tax +
                ", transactions=" + transactions +
                '}';
    }
}
