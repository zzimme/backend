package com.marine.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marine.backend.entity.Type;


public interface TypeRepository extends JpaRepository<Type, Integer>{

	public List<Type> findByIdIn(List<Integer> types);
}
