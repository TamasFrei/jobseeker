package com.example.jobseeker.repository;

import com.example.jobseeker.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByLocationContainingIgnoreCaseAndJobTitleContainingIgnoreCase(String location, String jobTitle);
}
