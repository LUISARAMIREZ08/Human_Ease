package com.example.server.repository;

import com.example.server.entity.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInterviewsRepository extends JpaRepository<Interviews, Long> {
}
