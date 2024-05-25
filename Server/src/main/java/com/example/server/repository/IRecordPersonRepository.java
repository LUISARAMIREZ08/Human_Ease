package com.example.server.repository;

import com.example.server.entity.RecordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecordPersonRepository extends JpaRepository<RecordUser, Long> {
}
