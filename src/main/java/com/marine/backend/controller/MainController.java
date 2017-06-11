package com.marine.backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marine.backend.domain.ItemRegisterModel;
import com.marine.backend.entity.Item;
import com.marine.backend.exception.AlreadyExistItemException;
import com.marine.backend.exception.CommonApiException;
import com.marine.backend.exception.NotExistItemException;
import com.marine.backend.exception.NotSupportedTypeException;
import com.marine.backend.service.ItemService;

@RestController
@RequestMapping(value = "/api/v1")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemService itemService;

	/**
	 * 방 정보 입력.
	 * @throws NotSupportedTypeException 
	 * @throws AlreadyExistItemException 
	 * @throws CommonApiException 
	 * */
	@RequestMapping(value = "/items", method = RequestMethod.POST)
	@ResponseBody
	public Object createItem(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@Validated @RequestBody ItemRegisterModel room) throws AlreadyExistItemException, NotSupportedTypeException, CommonApiException{
		
		Map<String,Object> map = itemService.createRoom(room);
		
		return map;
	}
	
	/**
	 * 방 정보 수정.
	 * @throws NotSupportedTypeException 
	 * @throws AlreadyExistItemException 
	 * @throws CommonApiException 
	 * @throws NotExistItemException 
	 * */
	@RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object modifyItem(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable(value="id") Integer id,
			@Validated @RequestBody ItemRegisterModel room
			) throws AlreadyExistItemException, NotSupportedTypeException, CommonApiException, NotExistItemException{
		
		room.setId(id);
		Map<String,Object> map = itemService.modifyItem(room);
		
		
		return map;
	}
	
	
	/**
	 * 방 상세 정보 조회.
	 * @throws NotExistItemException 
	 * 
	 * */
	@RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getItem(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable(value="id") Integer id
			) throws NotExistItemException {
		
		
		Item item = itemService.getItem(id);
		
		
		return item;
	}
	
	/**
	 * 방 목록 출력
	 *  
	 * 
	 * */
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ResponseBody
	public Object getListItem(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(name="lat_south", defaultValue="0.0", required=false) Double latSouth,
			@RequestParam(name="lat_north", defaultValue="0.0", required=false) Double latNorth,
			@RequestParam(name="lng_east", defaultValue="0.0", required=false) Double lngEast,
			@RequestParam(name="lng_west", defaultValue="0.0", required=false) Double lngWest,
			@RequestParam(name="type", defaultValue="", required=false) List<Integer> types,
			Pageable pageable
			) throws NotExistItemException {
		
		logger.info("types:{}",types.toString());
		return itemService.getItemList(latSouth, latNorth, lngEast, lngWest, types, pageable);
		
		
	}
	
}
