package me.home.dao;

import me.home.models.MoneyFlow;
import me.home.models.enums.MoneyFlowSubject;
import me.home.models.enums.MoneyFlowTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoneyFlowRepositoryTest {
    @Autowired
    private MoneyFlowRepository repository;
    private List<MoneyFlow> expected;

    @BeforeEach
    public void init() {
        expected = List.of(
                new MoneyFlow(1, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.of(2022, 1, 1)),
                new MoneyFlow(2, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.of(2022, 1, 2)),
                new MoneyFlow(3, MoneyFlowTypes.INCOME, MoneyFlowSubject.WORK, 600, LocalDate.of(2022, 1, 3)),
                new MoneyFlow(4, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.of(2022, 1, 4)),
                new MoneyFlow(5, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.CAR, 150, LocalDate.of(2022, 1, 5)),
                new MoneyFlow(6, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.APARTMENT, 50, LocalDate.of(2022, 1, 6))
        );
    }

    @Test
    public void smoke() {
        assertNotNull(repository);
    }

    @Test
    void findExpensesByPeriodTest() {
        LocalDate start = LocalDate.of(Year.now().getValue(), Month.JANUARY, 1);
        LocalDate end = LocalDate.of(Year.now().getValue(), Month.FEBRUARY, 1);
        List<MoneyFlow> actual = repository.findExpensesByPeriod(start, end);
        assertEquals(5, actual.size());
        assertIterableEquals(expected.stream().filter(e -> MoneyFlowTypes.EXPENSE.equals(e.getType())).toList(), actual);
    }

    @Test
    void findExpensesByPeriodTestWhenNothingFound() {
        LocalDate start = LocalDate.of(Year.now().getValue(), Month.FEBRUARY, 1);
        LocalDate end = LocalDate.of(Year.now().getValue(), Month.MARCH, 1);
        List<MoneyFlow> actual = repository.findExpensesByPeriod(start, end);
        assertTrue(actual.isEmpty());
    }

    @Test
    void findIncomesByPeriodTest() {
        LocalDate start = LocalDate.of(Year.now().getValue(), Month.JANUARY, 1);
        LocalDate end = LocalDate.of(Year.now().getValue(), Month.FEBRUARY, 1);
        List<MoneyFlow> actual = repository.findIncomesByPeriod(start, end);
        assertEquals(1, actual.size());
        assertIterableEquals(expected.stream().filter(e -> MoneyFlowTypes.INCOME.equals(e.getType())).toList(), actual);
    }

    @Test
    void findIncomesByPeriodTestWhenNothingFound() {
        LocalDate start = LocalDate.of(Year.now().getValue(), Month.FEBRUARY, 1);
        LocalDate end = LocalDate.of(Year.now().getValue(), Month.MARCH, 1);
        List<MoneyFlow> actual = repository.findIncomesByPeriod(start, end);
        assertTrue(actual.isEmpty());
    }
}