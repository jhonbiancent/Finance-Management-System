package com.finance.system.repository;

import com.finance.system.model.AccountsReceivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsReceivableRepository extends JpaRepository<AccountsReceivable, Long> {
}
