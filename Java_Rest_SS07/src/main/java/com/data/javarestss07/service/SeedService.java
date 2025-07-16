package com.data.javarestss07.service;
import com.data.javarestss07.model.entity.Seed;
import com.data.javarestss07.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class SeedService {
    @Autowired
    private SeedRepository seedRepository;

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }
    public Seed getSeedById(Long id) {
        return seedRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
    }
    public Seed addSeed(Seed seed) {
        return seedRepository.save(seed);
    }
    public Seed updateSeed(Long id, Seed seed) {
        seedRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
        return seedRepository.save(seed);
    }
    public void deleteSeed(Long id) {
        seedRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
        seedRepository.deleteById(id);
    }
}
