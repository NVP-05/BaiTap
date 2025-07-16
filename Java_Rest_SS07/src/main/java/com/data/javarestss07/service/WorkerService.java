package com.data.javarestss07.service;
import com.data.javarestss07.model.entity.Worker;
import com.data.javarestss07.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
    }
    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }
    public Worker updateWorker(Long id, Worker worker) {
        workerRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
        return workerRepository.save(worker);
    }
    public void deleteWorker(Long id) {
        workerRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
        workerRepository.deleteById(id);
    }
}
