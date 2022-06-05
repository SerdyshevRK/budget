package me.home.models;

import me.home.models.enums.MoneyFlowSubject;
import me.home.models.enums.MoneyFlowTypes;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class MoneyFlow {
    @Id
    private final long id;
    private final MoneyFlowTypes type;
    private final MoneyFlowSubject subject;
    private final int amount;
    private final LocalDate date;
    private String comment;

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

    public MoneyFlowTypes getType() {
        return type;
    }

    public MoneyFlowSubject getSubject() {
        return subject;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
