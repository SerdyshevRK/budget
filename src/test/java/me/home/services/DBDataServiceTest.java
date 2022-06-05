package me.home.services;

import me.home.dao.MoneyFlowRepository;
import me.home.models.MoneyFlow;
import me.home.models.enums.MoneyFlowSubject;
import me.home.models.enums.MoneyFlowTypes;
import me.home.services.interfaces.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringBootTest
class DBDataServiceTest {
    @Autowired
    private DataService underTest;
    @MockBean
    private MoneyFlowRepository repository;

    @Test
    void addMoneyFlowTest() {
        MoneyFlow flow = new MoneyFlow(1, MoneyFlowTypes.EXPENSE, MoneyFlowSubject.FOOD, 100, LocalDate.now());
        underTest.addMoneyFlow(flow);
        verify(repository, times(1)).save(flow);
    }
}