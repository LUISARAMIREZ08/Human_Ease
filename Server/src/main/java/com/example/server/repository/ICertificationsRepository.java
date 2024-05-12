package com.example.server.repository;

import com.example.server.entity.Certifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificationsRepository extends JpaRepository<Certifications, Long> {
}
