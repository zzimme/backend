package com.marine.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marine.backend.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{

}
