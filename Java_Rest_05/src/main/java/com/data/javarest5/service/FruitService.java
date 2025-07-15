package com.data.javarest5.service;
import com.data.javarest5.dto.FruitDTO;
import com.data.javarest5.model.entity.Fruit;

import java.util.List;

public interface FruitService {
    List<FruitDTO> getAllFruitDTOs();
    FruitDTO getFruitDTOById(Long id);
    FruitDTO createFruitAndReturnDTO(Fruit fruit);
    FruitDTO updateFruitAndReturnDTO(Long id, Fruit fruit);
    void deleteFruit(Long id);
}
