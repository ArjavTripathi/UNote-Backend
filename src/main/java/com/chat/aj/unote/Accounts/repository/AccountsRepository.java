package com.chat.aj.unote.Accounts.repository;


import com.chat.aj.unote.Accounts.Entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {
    boolean existsByUsername(String username);
    Optional<Accounts> findByUsername(String username);
}
