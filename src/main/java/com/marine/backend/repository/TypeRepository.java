package com.marine.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.marine.backend.entity.Type;

@RepositoryRestResource(exported = false)
public interface TypeRepository extends JpaRepository<Type, Integer>{

	public List<Type> findByIdIn(List<Integer> types);
}
