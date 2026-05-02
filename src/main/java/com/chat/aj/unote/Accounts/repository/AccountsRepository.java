package com.chat.aj.unote.Accounts.repository;


import com.chat.aj.unote.Accounts.Entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {

}
