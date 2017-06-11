package com.marine.backend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.marine.backend.entity.Item;
import com.marine.backend.entity.Type;
import com.marine.backend.repository.ItemRepository;
import com.marine.backend.repository.TypeRepository;

public class RoomTest extends BackendApplicationTests{
	
	@Autowired
    private ItemRepository roomRepo;
	
	@Autowired
	private TypeRepository typeRepo;
	
	@Test
	public void storeRoomTest(){
		
		Item room = new Item();
		
		room.setName("지하철역 주변 유일한 저렴한 도시형주택!");
		room.setLat(37.588792);
		room.setLng(126.944094);
		room.setRent(0);
		room.setDeposit(20000);
		room.setDescription("지하철역 주변 유일한 저렴한 도시형주택");
		
		
		Type type = typeRepo.findOne(1);
		System.out.println("type:"+type.getName());
		room.setType(type);
		
		roomRepo.save(room);
	
	}
	
}
