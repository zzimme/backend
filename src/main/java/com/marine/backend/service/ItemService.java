package com.marine.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.marine.backend.domain.ItemRegisterModel;
import com.marine.backend.domain.ItemResponseModel;
import com.marine.backend.exception.AlreadyExistItemException;
import com.marine.backend.exception.CommonApiException;
import com.marine.backend.exception.NotExistItemException;
import com.marine.backend.exception.NotSupportedTypeException;

public interface ItemService {

	public Map<String, Object> createItem(ItemRegisterModel item) throws AlreadyExistItemException, NotSupportedTypeException, CommonApiException;
	public Map<String, Object> modifyItem(ItemRegisterModel item) throws NotExistItemException, NotSupportedTypeException, CommonApiException;
	public ItemResponseModel getItem(Integer id) throws NotExistItemException;
	public List<ItemResponseModel> getItemList(Double latSouth, Double latNorth, Double lngEast, Double lngWest, List<Integer> types, Pageable pageable);
}
