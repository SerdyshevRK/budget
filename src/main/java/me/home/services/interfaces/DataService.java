package me.home.services.interfaces;

import me.home.models.MoneyFlow;
import me.home.models.enums.MoneyFlowSubject;

import java.time.Month;

public interface DataService {
    void addMoneyFlow(MoneyFlow flow);
    int countSpendingByMonth(Month month);
    int countSpendingBySubjectAndMonth(MoneyFlowSubject subject, Month month);
    int countIncomesByMonth(Month month);

    int countIncomesBySubjectAndMonth(MoneyFlowSubject subject, Month month);
}
