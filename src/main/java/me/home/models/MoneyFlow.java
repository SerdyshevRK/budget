package me.home.models;

import me.home.models.enums.MoneyFlowSubject;
import me.home.models.enums.MoneyFlowTypes;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "money`")
public class MoneyFlow {
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private MoneyFlowTypes type;
    @Enumerated(EnumType.STRING)
    private MoneyFlowSubject subject;
    private int amount;
    @Column(name = "occurs_date")
    private LocalDate date;
    private String comment;

    public MoneyFlow() {
    }

    public MoneyFlow(long id, MoneyFlowTypes type, MoneyFlowSubject subject, int amount, LocalDate date) {
        this.id = id;
        this.type = type;
        this.subject = subject;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MoneyFlowTypes getType() {
        return type;
    }

    public void setType(MoneyFlowTypes type) {
        this.type = type;
    }

    public MoneyFlowSubject getSubject() {
        return subject;
    }

    public void setSubject(MoneyFlowSubject subject) {
        this.subject = subject;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + type.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + amount;
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof MoneyFlow other)) return false;
        return id == other.id && type.equals(other.type) && subject.equals(other.subject) && amount == other.amount && date.equals(other.date);
    }
}
