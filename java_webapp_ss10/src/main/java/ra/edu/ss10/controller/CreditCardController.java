package ra.edu.ss10.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss10.model.dto.response.ApiDataResponse;
import ra.edu.ss10.model.entity.CreditCard;
import ra.edu.ss10.service.CreditCardService;

import java.util.UUID;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<CreditCard>> create(@Valid @RequestBody CreditCard creditCard) {
        return new ResponseEntity<>(new ApiDataResponse<>(creditCardService.create(creditCard),HttpStatus.CREATED), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<CreditCard>> updateLimit(@PathVariable Long id,
                                                                   @RequestParam Double limit) {
        return new ResponseEntity<>(new ApiDataResponse<>(creditCardService.updateLimit(id, limit), HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiDataResponse<CreditCard>> updateStatus(@PathVariable Long id,
                                                                    @RequestParam String status) {
       return new ResponseEntity<>(new ApiDataResponse<>(creditCardService.updateStatus(id, status), HttpStatus.OK), HttpStatus.OK);
    }
}
