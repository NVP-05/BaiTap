package ra.edu.ss10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss10.model.entity.Account;
import ra.edu.ss10.repository.AccountRepository;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong ton toi tai khoan"));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account updated, Long id) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong ton tai tai khoan"));
        acc.setId(id);
        return accountRepository.save(acc);
    }

    @Override
    public void deleteById(Long id) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong ton tai tai khoan"));
        accountRepository.delete(acc);

    }
}
