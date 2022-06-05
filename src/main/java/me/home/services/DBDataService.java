package me.home.services;

import me.home.dao.MoneyFlowRepository;
import me.home.models.MoneyFlow;
import me.home.models.enums.MoneyFlowSubject;
import me.home.services.interfaces.DataService;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

@Service
public class DBDataService implements DataService {
    private final MoneyFlowRepository repository;

    public DBDataService(MoneyFlowRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMoneyFlow(MoneyFlow flow) {
        repository.save(flow);
    }

    @Override
    public int countSpendingByMonth(Month month) {
        List<MoneyFlow> moneyFlow = repository.findExpensesByMonth(month);
        return countSum(moneyFlow, null);
    }

    @Override
    public int countSpendingBySubjectAndMonth(MoneyFlowSubject subject, Month month) {
        List<MoneyFlow> moneyFlow = repository.findExpensesByMonth(month);
        return countSum(moneyFlow, subject);
    }

    @Override
    public int countIncomesByMonth(Month month) {
        List<MoneyFlow> moneyFlow = repository.findIncomesByMonth(month);
        return countSum(moneyFlow, null);
    }

    @Override
    public int countIncomesBySubjectAndMonth(MoneyFlowSubject subject, Month month) {
        List<MoneyFlow> moneyFlow = repository.findIncomesByMonth(month);
        return countSum(moneyFlow, subject);
    }

    private int countSum(List<MoneyFlow> values, MoneyFlowSubject subject) {
        if (subject == null)
            return values.stream().mapToInt(MoneyFlow::getAmount).sum();
        return values.stream().filter(flow -> subject.equals(flow.getSubject()))
                .mapToInt(MoneyFlow::getAmount).sum();
    }
}
