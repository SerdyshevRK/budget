package me.home.services;

import me.home.dao.MoneyFlowRepository;
import me.home.models.MoneyFlow;
import me.home.models.enums.MoneyFlowSubject;
import me.home.services.interfaces.DataService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
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
        LocalDate[] dates = calculateDateDelta(month);
        List<MoneyFlow> moneyFlow = repository.findExpensesByPeriod(dates[0], dates[1]);
        return countSum(moneyFlow, null);
    }

    @Override
    public int countSpendingBySubjectAndMonth(MoneyFlowSubject subject, Month month) {
        LocalDate[] dates = calculateDateDelta(month);
        List<MoneyFlow> moneyFlow = repository.findExpensesByPeriod(dates[0], dates[1]);
        return countSum(moneyFlow, subject);
    }

    @Override
    public int countIncomesByMonth(Month month) {
        LocalDate[] dates = calculateDateDelta(month);
        List<MoneyFlow> moneyFlow = repository.findIncomesByPeriod(dates[0], dates[1]);
        return countSum(moneyFlow, null);
    }

    @Override
    public int countIncomesBySubjectAndMonth(MoneyFlowSubject subject, Month month) {
        LocalDate[] dates = calculateDateDelta(month);
        List<MoneyFlow> moneyFlow = repository.findIncomesByPeriod(dates[0], dates[1]);
        return countSum(moneyFlow, subject);
    }

    private int countSum(List<MoneyFlow> values, MoneyFlowSubject subject) {
        if (subject == null)
            return values.stream().mapToInt(MoneyFlow::getAmount).sum();
        return values.stream().filter(flow -> subject.equals(flow.getSubject()))
                .mapToInt(MoneyFlow::getAmount).sum();
    }

    private LocalDate[] calculateDateDelta(Month month) {
        LocalDate[] delta = new LocalDate[2];
        delta[0] = LocalDate.of(Year.now().getValue(), month, 1);
        delta[1] = delta[0].plusMonths(1);
        return delta;
    }
}
