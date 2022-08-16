package com.leftovers.order.account.dao;


import com.leftovers.order.account.model.account;
import com.leftovers.order.account.repository.accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class accountDAO {

    @Autowired
    accountRepository fdr;

    public boolean addAccount(account newAccount) {

        try {
            fdr.save(newAccount);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<account> getAccountById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    /*
    public List<account> getAccountByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }
*/
    public List<account> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<account>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateAccount(account updateAccount) {
        try {
            fdr.save(updateAccount);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}