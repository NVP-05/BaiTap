package com.data.javarestss07.controller;

import com.data.javarestss07.model.entity.Harvest;
import com.data.javarestss07.model.response.DataResponse;
import com.data.javarestss07.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("harvests")
public class HarvestController {
    @Autowired
    private HarvestService harvestService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Harvest>>> getHarvests() {
        return new ResponseEntity<>(new DataResponse<>(harvestService.getAllHarvests(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Harvest>> getHarvestById(@PathVariable Long id) {
        return new ResponseEntity<>(new DataResponse<>(harvestService.getHarvestById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Harvest>> addHarvest(@RequestBody Harvest harvest) {
        return new ResponseEntity<>(new DataResponse<>(harvestService.addHarvest(harvest), HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
