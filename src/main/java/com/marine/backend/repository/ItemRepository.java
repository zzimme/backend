package com.marine.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marine.backend.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{

	List<Item> findByLatBetweenAndLngBetweenAndTypeIn(Float latStart, Float latEnd,Float lngStart, Float lngEnd, List<Integer> types);
	List<Item> findAll(Specification<Item> spec, Pageable pageable);
}
