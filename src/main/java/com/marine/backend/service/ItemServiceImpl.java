package com.marine.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.marine.backend.domain.ItemRegisterModel;
import com.marine.backend.entity.Item;
import com.marine.backend.entity.Type;
import com.marine.backend.exception.AlreadyExistItemException;
import com.marine.backend.exception.CommonApiException;
import com.marine.backend.exception.NotExistItemException;
import com.marine.backend.exception.NotSupportedTypeException;
import com.marine.backend.repository.ItemRepository;
import com.marine.backend.repository.TypeRepository;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private TypeRepository typeRepository;

	@Override
	public Map<String, Object> createRoom(ItemRegisterModel item) throws AlreadyExistItemException, NotSupportedTypeException, CommonApiException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Item entity = new Item();
		
		Type type = typeRepository.findOne(item.getType());
		
		if(type == null){
			throw new NotSupportedTypeException("NotSupportedType",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try{
			entity.setName(item.getName());
			entity.setLat(item.getLat());
			entity.setLng(item.getLng());
			entity.setRent(item.getRent());
			entity.setDeposit(item.getDeposit());
			entity.setDescription(item.getDescription());
			entity.setType(type);
			
			itemRepository.save(entity);
		} catch ( Exception ex){
			throw new CommonApiException(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		map.put("id", entity.getId());
		map.put("message", "created");
		
		return map;
	}

	@Override
	public Map<String, Object> modifyItem(ItemRegisterModel item) throws NotExistItemException, NotSupportedTypeException, CommonApiException {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Item entity = itemRepository.findOne(item.getId());
		
		if(entity == null){
			throw new NotExistItemException("NotExistRoom", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Type type = typeRepository.findOne(item.getType());
		
		if(type == null){
			throw new NotSupportedTypeException("NotSupportedType",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try{
			entity.setName(item.getName());
			entity.setLat(item.getLat());
			entity.setLng(item.getLng());
			entity.setRent(item.getRent());
			entity.setDeposit(item.getDeposit());
			entity.setDescription(item.getDescription());
			entity.setType(type);
			
			itemRepository.save(entity);
			
		}catch(Exception ex){
			throw new CommonApiException(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		map.put("id", entity.getId());
		map.put("message", "modified");
		
		return map;
	}

	@Override
	public Item getItem(Integer id) throws NotExistItemException {
		Item entity = itemRepository.findOne(id);
		
		if(entity == null){
			throw new NotExistItemException("NotExistRoom", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	
}
