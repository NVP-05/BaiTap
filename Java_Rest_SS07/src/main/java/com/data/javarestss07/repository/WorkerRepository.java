package com.data.javarestss07.repository;
import com.data.javarestss07.model.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}