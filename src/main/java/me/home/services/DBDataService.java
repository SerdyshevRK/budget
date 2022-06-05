package me.home.services;

import me.home.dao.MoneyFlowRepository;
import me.home.models.MoneyFlow;
import me.home.services.interfaces.DataService;
import org.springframework.stereotype.Service;

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
}
