package ra.edu.ss10.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss10.model.dto.response.ApiDataResponse;
import ra.edu.ss10.model.entity.Account;
import ra.edu.ss10.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Account>>> getAllAccounts() {
        return new ResponseEntity<>(new ApiDataResponse<>(accountService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiDataResponse<Account>> createAccount(Account account) {
        return new ResponseEntity<>(new ApiDataResponse<>(accountService.save(account), HttpStatus.CREATED), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Account>> updateAccount(@PathVariable("id") Long id, @RequestBody Account updatedAccount) {
       return new ResponseEntity<>(new ApiDataResponse<>(accountService.update(updatedAccount, id), HttpStatus.OK), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<Account> findByCccd(@PathVariable String cccd) {
        return accountService.findAll().stream()
                .filter(a -> a.getCccd().equals(cccd))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ra.edu.ss10.exception.NotFoundException("Account not found with CCCD: " + cccd));
    }
}
