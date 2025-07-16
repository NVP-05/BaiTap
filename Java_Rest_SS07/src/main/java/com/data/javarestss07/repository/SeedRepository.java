package com.data.javarestss07.repository;
import com.data.javarestss07.model.entity.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SeedRepository extends JpaRepository<Seed, Long> {

}
