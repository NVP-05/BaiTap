package com.data.javarest5.controller;

import com.data.javarest5.dto.FruitDTO;
import com.data.javarest5.model.entity.Fruit;
import com.data.javarest5.model.response.DataResponse;
import com.data.javarest5.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    public ResponseEntity<DataResponse<List<FruitDTO>>> getAllFruits() {
        List<FruitDTO> fruits = fruitService.getAllFruitDTOs();
        return new ResponseEntity<>(new DataResponse<>(fruits, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<FruitDTO>> createFruit(@RequestBody Fruit fruit) {
        FruitDTO created = fruitService.createFruitAndReturnDTO(fruit);
        return new ResponseEntity<>(new DataResponse<>(created, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<FruitDTO>> updateFruit(@PathVariable Long id, @RequestBody Fruit fruit) {
        FruitDTO updated = fruitService.updateFruitAndReturnDTO(id, fruit);
        return new ResponseEntity<>(new DataResponse<>(updated, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return new ResponseEntity<>(new DataResponse<>(null, HttpStatus.OK), HttpStatus.OK);
    }
}
