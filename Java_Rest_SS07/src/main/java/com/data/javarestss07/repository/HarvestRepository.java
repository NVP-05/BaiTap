package com.data.javarestss07.repository;
import com.data.javarestss07.model.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}