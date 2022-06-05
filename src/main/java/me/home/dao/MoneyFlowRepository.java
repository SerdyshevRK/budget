package me.home.dao;

import me.home.models.MoneyFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MoneyFlowRepository extends JpaRepository<MoneyFlow, Long> {
    @Query(nativeQuery = true, value = "select * from money where type='EXPENSE' and occurs_date >= :start and occurs_date < :end")
    List<MoneyFlow> findExpensesByPeriod(@Param("start") LocalDate start, @Param("end") LocalDate end);
    @Query(nativeQuery = true, value = "select * from money where type='INCOME' and occurs_date >= :start and occurs_date < :end")
    List<MoneyFlow> findIncomesByPeriod(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
