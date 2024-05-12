package com.example.server.repository;

import com.example.server.entity.Requirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequirementsRepository extends JpaRepository<Requirements, Long> {
}
