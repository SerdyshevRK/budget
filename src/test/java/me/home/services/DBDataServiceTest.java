package me.home.services;

import me.home.dao.MoneyFlowRepository;
import me.home.models.MoneyFlow;
import me.home.models.enums.MoneyFlowSubject;
import me.home.models.enums.MoneyFlowTypes;
import me.home.services.interfaces.DataService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class DBDataServiceTest {
    @Autowired
    private DataService underTest;
    @MockBean
    private MoneyFlowRepository repository;

    @Test
    void smoke() {
        assertNotNull(underTest);
    }

    @Test
    void addMoneyFlowTest() {
        MoneyFlow flow = new MoneyFlow(1, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.now());
        underTest.addMoneyFlow(flow);
        verify(repository, times(1)).save(flow);
    }

    @Test
    void countSpendingByMonthTest() {
        List<MoneyFlow> flows = new ArrayList<>() {{
            add(new MoneyFlow(1, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.now()));
            add(new MoneyFlow(2, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.now()));
        }};
        Month month = Month.of(LocalDate.now().getMonthValue());
        when(repository.findExpensesByMonth(month)).thenReturn(flows);
        int actual = underTest.countSpendingByMonth(month);
        verify(repository, times(1)).findExpensesByMonth(month);
        assertEquals(200, actual);
    }

    @Test
    void countSpendingByMonthTestWhenNothingFound() {
        when(repository.findExpensesByMonth(any())).thenReturn(Collections.emptyList());
        int actual = underTest.countSpendingByMonth(Month.of(LocalDate.now().getMonthValue()));
        verify(repository, times(1)).findExpensesByMonth(any());
        assertEquals(0, actual);
    }

    @Test
    void countSpendingBySubjectAndMonthTest() {
        List<MoneyFlow> flows = new ArrayList<>() {{
            add(new MoneyFlow(1, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.now()));
            add(new MoneyFlow(2, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.now()));
            add(new MoneyFlow(3, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.CAR, 100, LocalDate.now()));
        }};
        Month month = Month.of(LocalDate.now().getMonthValue());
        when(repository.findExpensesByMonth(month)).thenReturn(flows);
        int actual = underTest.countSpendingBySubjectAndMonth(MoneyFlowSubject.FOOD, month);
        verify(repository, times(1)).findExpensesByMonth(month);
        assertEquals(200, actual);
    }

    @Test
    void countSpendingBySubjectAndMonthTestWhenNothingFound() {
        when(repository.findExpensesByMonth(any())).thenReturn(Collections.emptyList());
        int actual = underTest.countSpendingBySubjectAndMonth(MoneyFlowSubject.FOOD, LocalDate.now().getMonth());
        verify(repository, times(1)).findExpensesByMonth(any());
        assertEquals(0, actual);
    }

    @Test
    void countIncomesByMonthTest() {
        List<MoneyFlow> flows = new ArrayList<>() {{
            add(new MoneyFlow(1, MoneyFlowTypes.INCOME, MoneyFlowSubject.WORK, 100, LocalDate.now()));
            add(new MoneyFlow(2, MoneyFlowTypes.INCOME, MoneyFlowSubject.WORK, 100, LocalDate.now()));
        }};
        when(repository.findIncomesByMonth(any())).thenReturn(flows);
        int actual = underTest.countIncomesByMonth(LocalDate.now().getMonth());
        verify(repository, times(1)).findIncomesByMonth(LocalDate.now().getMonth());
        assertEquals(200, actual);
    }

    @Test
    void countIncomesByMonthTestWhenNothingFound() {
        when(repository.findIncomesByMonth(any())).thenReturn(Collections.emptyList());
        int actual = underTest.countIncomesByMonth(LocalDate.now().getMonth());
        verify(repository, times(1)).findIncomesByMonth(LocalDate.now().getMonth());
        assertEquals(0, actual);
    }

    @Test
    void countIncomesBySubjectAndMonthTest() {
        List<MoneyFlow> flows = new ArrayList<>() {{
            add(new MoneyFlow(1, MoneyFlowTypes.INCOME, MoneyFlowSubject.WORK, 100, LocalDate.now()));
            add(new MoneyFlow(2, MoneyFlowTypes.INCOME, MoneyFlowSubject.WORK, 100, LocalDate.now()));
            add(new MoneyFlow(2, MoneyFlowTypes.INCOME, MoneyFlowSubject.FRIENDS, 100, LocalDate.now()));
        }};
        when(repository.findIncomesByMonth(any())).thenReturn(flows);
        int actual = underTest.countIncomesBySubjectAndMonth(MoneyFlowSubject.FRIENDS, LocalDate.now().getMonth());
        verify(repository, times(1)).findIncomesByMonth(LocalDate.now().getMonth());
        assertEquals(100, actual);
    }

    @Test
    void countIncomesBySubjectAndMonthTestWhenNothingFound() {
        when(repository.findIncomesByMonth(any())).thenReturn(Collections.emptyList());
        int actual = underTest.countIncomesBySubjectAndMonth(MoneyFlowSubject.WORK, LocalDate.now().getMonth());
        verify(repository, times(1)).findIncomesByMonth(LocalDate.now().getMonth());
        assertEquals(0, actual);
    }
}