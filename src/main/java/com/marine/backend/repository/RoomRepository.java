package com.marine.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marine.backend.domain.Room;


public interface RoomRepository extends JpaRepository<Room, Integer>{

}
