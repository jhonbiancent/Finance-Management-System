package com.finance.system.repository;

import com.finance.system.model.AccountsPayable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsPayableRepository extends JpaRepository<AccountsPayable, Long> {
}
