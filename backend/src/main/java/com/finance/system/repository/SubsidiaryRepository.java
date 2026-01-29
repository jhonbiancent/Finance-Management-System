package com.finance.system.repository;

import com.finance.system.model.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Long> {
}
