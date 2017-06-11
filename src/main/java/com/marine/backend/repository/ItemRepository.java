package com.marine.backend.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marine.backend.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{

	PageImpl<Item> findAll(Specification<Item> spec, Pageable pageable);

}
