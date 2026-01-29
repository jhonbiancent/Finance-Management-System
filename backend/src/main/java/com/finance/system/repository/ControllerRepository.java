package com.finance.system.repository;

import com.finance.system.model.Controller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControllerRepository extends JpaRepository<Controller, Long> {
}
