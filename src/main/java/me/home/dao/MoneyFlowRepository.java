package me.home.dao;

import me.home.models.MoneyFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
public interface MoneyFlowRepository extends JpaRepository<MoneyFlow, Long> {
    List<MoneyFlow> findExpensesByMonth(Month month);
    List<MoneyFlow> findIncomesByMonth(Month month);
}
