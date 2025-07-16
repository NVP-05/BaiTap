package com.data.javarestss07.controller;
import com.data.javarestss07.model.entity.Seed;
import com.data.javarestss07.model.response.DataResponse;
import com.data.javarestss07.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seeds")
public class SeedController {
    @Autowired
    SeedService seedService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Seed>>> getSeeds() {
        return new ResponseEntity<>(new DataResponse<>(seedService.getAllSeeds(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Seed>> getSeedById(@PathVariable Long id) {
        return new ResponseEntity<>(new DataResponse<>(seedService.getSeedById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Seed>> addSeed(@RequestBody Seed seed) {
        return new ResponseEntity<>(new DataResponse<>(seedService.addSeed(seed), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Seed>> updateSeed(@PathVariable Long id, @RequestBody Seed seed) {
        return new ResponseEntity<>(new DataResponse<>(seedService.updateSeed(id,seed), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeed(@PathVariable Long id) {
        seedService.deleteSeed(id);
        return new ResponseEntity<>(new DataResponse<>("Xóa thành công!", HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }
}
