package com.data.javarest5.service;
import com.data.javarest5.dto.FruitDTO;
import com.data.javarest5.model.entity.Fruit;
import com.data.javarest5.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceImp implements FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public List<FruitDTO> getAllFruitDTOs() {
        return fruitRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FruitDTO getFruitDTOById(Long id) {
        Fruit fruit = fruitRepository.findById(id).orElse(null);
        return fruit != null ? convertToDTO(fruit) : null;
    }

    @Override
    public FruitDTO createFruitAndReturnDTO(Fruit fruit) {
        Fruit saved = fruitRepository.save(fruit);
        return convertToDTO(saved);
    }

    @Override
    public FruitDTO updateFruitAndReturnDTO(Long id, Fruit fruit) {
        Fruit existingFruit = fruitRepository.findById(id).orElse(null);
        if (existingFruit != null) {
            existingFruit.setName(fruit.getName());
            existingFruit.setPrice(fruit.getPrice());
            existingFruit.setStock(fruit.getStock());
            existingFruit.setStatus(fruit.getStatus());
            existingFruit.setCreatedAt(fruit.getCreatedAt());
            Fruit updated = fruitRepository.save(existingFruit);
            return convertToDTO(updated);
        }
        return null;
    }

    @Override
    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }

    private FruitDTO convertToDTO(Fruit fruit) {
        return new FruitDTO(fruit.getId(), fruit.getName(), fruit.getPrice());
    }
}
