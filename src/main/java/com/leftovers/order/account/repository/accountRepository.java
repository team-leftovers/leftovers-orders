package com.leftovers.order.account.repository;

import com.leftovers.order.account.model.account;
import org.springframework.data.repository.CrudRepository;


public interface accountRepository extends CrudRepository<account, Integer> {
        account findById(long id);
}