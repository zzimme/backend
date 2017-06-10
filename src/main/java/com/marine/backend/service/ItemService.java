package com.marine.backend.service;

import java.util.Map;

import com.marine.backend.domain.ItemRegisterModel;
import com.marine.backend.entity.Item;
import com.marine.backend.exception.AlreadyExistItemException;
import com.marine.backend.exception.CommonApiException;
import com.marine.backend.exception.NotExistItemException;
import com.marine.backend.exception.NotSupportedTypeException;

public interface ItemService {

	public Map<String, Object> createRoom(ItemRegisterModel room) throws AlreadyExistItemException, NotSupportedTypeException, CommonApiException;
	public Map<String, Object> modifyItem(ItemRegisterModel room) throws NotExistItemException, NotSupportedTypeException, CommonApiException;
	public Item getItem(Integer id) throws NotExistItemException;
}
