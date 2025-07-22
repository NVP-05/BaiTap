package ra.edu.ss10.service;

import ra.edu.ss10.model.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findById(Long id);
    Account save(Account account);
    Account update(Account updated, Long id);
    void deleteById(Long id);

}
