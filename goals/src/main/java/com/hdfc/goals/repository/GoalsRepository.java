package com.hdfc.goals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.goals.model.Goals;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Long> {
	List<Goals> findByUsername(String username);
}