package me.home.dao;

import me.home.models.MoneyFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyFlowRepository extends JpaRepository<MoneyFlow, Long> {
}
