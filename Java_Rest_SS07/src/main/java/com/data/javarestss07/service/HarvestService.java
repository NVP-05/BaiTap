package com.data.javarestss07.service;

import com.data.javarestss07.model.entity.Harvest;
import com.data.javarestss07.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HarvestService {
    @Autowired
    private HarvestRepository harvestRepository;

    public List<Harvest> getAllHarvests() {
        return harvestRepository.findAll();
    }
    public Harvest getHarvestById(Long id) {
        return harvestRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
    }
    public Harvest addHarvest(Harvest harvest) {
        return harvestRepository.save(harvest);
    }
}
