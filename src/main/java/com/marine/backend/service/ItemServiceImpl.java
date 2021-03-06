package com.marine.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.marine.backend.Specification.ItemSpecification;
import com.marine.backend.domain.ItemRegisterModel;
import com.marine.backend.domain.ItemResponseModel;
import com.marine.backend.entity.Item;
import com.marine.backend.entity.Type;
import com.marine.backend.exception.AlreadyExistItemException;
import com.marine.backend.exception.CommonApiException;
import com.marine.backend.exception.NotExistItemException;
import com.marine.backend.exception.NotSupportedTypeException;
import com.marine.backend.repository.ItemRepository;
import com.marine.backend.repository.TypeRepository;

@Service
public class ItemServiceImpl implements ItemService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private TypeRepository typeRepository;

	@Override
	public Map<String, Object> createItem(ItemRegisterModel item)
			throws AlreadyExistItemException, NotSupportedTypeException, CommonApiException {

		Map<String, Object> map = new HashMap<String, Object>();
		Item entity = new Item();

		Type type = typeRepository.findOne(item.getType());

		if (type == null) {
			throw new NotSupportedTypeException("NotSupportedType", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			entity.setName(item.getName());
			entity.setLat(item.getLat());
			entity.setLng(item.getLng());
			entity.setRent(item.getRent());
			entity.setDeposit(item.getDeposit());
			entity.setDescription(item.getDescription());
			entity.setType(type);

			itemRepository.save(entity);
		} catch (Exception ex) {
			throw new CommonApiException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		map.put("id", entity.getId());
		map.put("message", "created");

		return map;
	}

	@Override
	public Map<String, Object> modifyItem(ItemRegisterModel item)
			throws NotExistItemException, NotSupportedTypeException, CommonApiException {
		
		Map<String, Object> map = new HashMap<String, Object>();

		Item entity = itemRepository.findOne(item.getId());

		if (entity == null) {
			throw new NotExistItemException("NotExistRoom", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Type type = typeRepository.findOne(item.getType());

		if (type == null) {
			throw new NotSupportedTypeException("NotSupportedType", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			entity.setName(item.getName());
			entity.setLat(item.getLat());
			entity.setLng(item.getLng());
			entity.setRent(item.getRent());
			entity.setDeposit(item.getDeposit());
			entity.setDescription(item.getDescription());
			entity.setType(type);

			itemRepository.save(entity);

		} catch (Exception ex) {
			throw new CommonApiException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		map.put("id", entity.getId());
		map.put("message", "modified");

		return map;
	}

	@Override
	public ItemResponseModel getItem(Integer id) throws NotExistItemException {
		Item entity = itemRepository.findOne(id);

		if (entity == null) {
			throw new NotExistItemException("NotExistRoom", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		ItemResponseModel model = new ItemResponseModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setLat(entity.getLat());
		model.setLng(entity.getLng());
		model.setRent(entity.getRent());
		model.setDeposit(entity.getDeposit());
		model.setDescription(entity.getDescription());
		model.setType(entity.getType().getName());
		
		return model;
	}

	@Override
	public List<ItemResponseModel> getItemList(Double latSouth, Double latNorth, Double lngEast, Double lngWest, List<Integer> types,
			Pageable pageable) {

		Specifications<Item> specifications = Specifications.where(ItemSpecification.basic());

		if (latSouth > 0 && latNorth > 0) {
			specifications = specifications.and(ItemSpecification.betweenLat(latSouth, latNorth));
		}

		if (lngEast > 0 && lngWest > 0) {
			specifications = specifications.and(ItemSpecification.betweenLng(lngEast, lngWest));
		}

		if (!types.isEmpty()) {
			List<Type> typeList = typeRepository.findByIdIn(types);
			if(!typeList.isEmpty())
				specifications = specifications.and(ItemSpecification.typeIn(typeList));
		}

		
		PageImpl<Item> list = itemRepository.findAll(specifications, pageable);
		
		List<ItemResponseModel> result = new ArrayList<ItemResponseModel>();
		
		for (Item item : list.getContent()) {
			
			ItemResponseModel model = new ItemResponseModel();
			model.setId(item.getId());
			model.setName(item.getName());
			model.setLat(item.getLat());
			model.setLng(item.getLng());
			model.setRent(item.getRent());
			model.setDeposit(item.getDeposit());
			model.setDescription(item.getDescription());
			model.setType(item.getType().getName());
			
			result.add(model);
		}
		return result;
	}

}
