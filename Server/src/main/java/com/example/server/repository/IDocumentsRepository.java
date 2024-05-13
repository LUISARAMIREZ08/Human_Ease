package com.example.server.repository;

import com.example.server.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDocumentsRepository extends JpaRepository<Documents, Long> {
    List<Documents> findByPersonCardId(Long cardId);
}
