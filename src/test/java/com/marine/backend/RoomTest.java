package com.marine.backend;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.marine.backend.model.Room;
import com.marine.backend.model.Type;

public class RoomTest extends BackendApplicationTests{
	
	@Autowired
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
	
	@Test
	public void storeRoomTest(){
		
		Room room = new Room();
		
		room.setName("지하철역 주변 유일한 저렴한 도시형주택!");
		room.setLat(37.588792);
		room.setLng(126.944094);
		
		/*Type type = new Type();
		type.setName("오피스텔");*/
		
	}
	
}
