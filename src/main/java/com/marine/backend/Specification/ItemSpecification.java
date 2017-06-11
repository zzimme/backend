package com.marine.backend.Specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.marine.backend.entity.Item;
import com.marine.backend.entity.Item_;
import com.marine.backend.entity.Type;

public class ItemSpecification {

	public static Specification<Item> basic(){
		return new Specification<Item>(){
			
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.ge(root.get(Item_.id), 1);
			}
		};
	}
	
	public static Specification<Item> betweenLat(final Double latSouth, final Double latNorth){
		return new Specification<Item>(){
			
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.between(root.get(Item_.lat), latSouth, latNorth);
			}
		};
	}
	
	public static Specification<Item> betweenLng(final Double lngEast, final Double lngWest){
		return new Specification<Item>(){
			
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.between(root.get(Item_.lng), lngEast, lngWest);
			}
		};
	}
	
	public static Specification<Item> typeIn(final List<Type> types){
		return new Specification<Item>(){
			
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return root.get(Item_.type).in(types);
			}
		};
	}
}
