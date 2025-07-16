package com.data.javarestss07.controller;

import com.data.javarestss07.model.entity.Worker;
import com.data.javarestss07.model.response.DataResponse;
import com.data.javarestss07.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Worker>>> getWorkers() {
        return new ResponseEntity<>(new DataResponse<>(workerService.getAllWorkers(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Worker>> getWorkerById(@PathVariable Long id) {
        return new ResponseEntity<>(new DataResponse<>(workerService.getWorkerById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Worker>> addWorker(@RequestBody Worker worker) {
        return new ResponseEntity<>(new DataResponse<>(workerService.addWorker(worker), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Worker>> updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        return new ResponseEntity<>(new DataResponse<>(workerService.updateWorker(id, worker), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return new ResponseEntity<>(new DataResponse<>("Xóa thành công!", HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }
}