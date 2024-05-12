package com.example.server.repository;

import com.example.server.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentsRepository extends JpaRepository<Departments, Long> {
}
