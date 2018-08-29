package com.abc.springbootactiviti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abc.springbootactiviti.ActivitiType;
import com.abc.springbootactiviti.beans.UserType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
//import com.google.common.collect.Multimap;
//import com.google.common.collect.Multimaps;


@Service
public class UserServiceImpl implements UserService{
	
	private ListMultimap<String,UserType> map= this.createTestData();
	
	public List<UserType> getRealUsers(String userId){
//		if() {}
		return map.get(userId);
	}
	
	private ListMultimap<String,UserType> createTestData(){
		ListMultimap<String,UserType> map  = ArrayListMultimap.create();
		//选择单人
		map.put("user01", new UserType(ActivitiType.USER,"user01"));
		map.put("user02", new UserType(ActivitiType.USER,"user02"));
		map.put("user03", new UserType(ActivitiType.USER,"user03"));
		map.put("user04", new UserType(ActivitiType.USER,"user04"));
		map.put("user05", new UserType(ActivitiType.USER,"user05"));
		map.put("user06", new UserType(ActivitiType.USER,"user06"));
		map.put("user11", new UserType(ActivitiType.USER,"user11"));
		map.put("user12", new UserType(ActivitiType.USER,"user12"));
		map.put("user13", new UserType(ActivitiType.USER,"user13"));
		map.put("user14", new UserType(ActivitiType.USER,"user14"));
		map.put("user15", new UserType(ActivitiType.USER,"user15"));
		map.put("user16", new UserType(ActivitiType.USER,"user16"));
		map.put("user21", new UserType(ActivitiType.USER,"user21"));
		map.put("user22", new UserType(ActivitiType.USER,"user22"));
		map.put("user23", new UserType(ActivitiType.USER,"user23"));
		map.put("user24", new UserType(ActivitiType.USER,"user24"));
		map.put("user25", new UserType(ActivitiType.USER,"user25"));
		map.put("user26", new UserType(ActivitiType.USER,"user26"));
		map.put("user31", new UserType(ActivitiType.USER,"user31"));
		map.put("user32", new UserType(ActivitiType.USER,"user32"));
		map.put("user33", new UserType(ActivitiType.USER,"user33"));
		map.put("user34", new UserType(ActivitiType.USER,"user34"));
		map.put("user35", new UserType(ActivitiType.USER,"user35"));
		map.put("user36", new UserType(ActivitiType.USER,"user36"));
		//组
		map.put("group0", new UserType(ActivitiType.GROUP,"group0"));
		map.put("group1", new UserType(ActivitiType.GROUP,"group1"));
		map.put("group2", new UserType(ActivitiType.GROUP,"group2"));
		map.put("group3", new UserType(ActivitiType.GROUP,"group3"));
		//每次选中多人
		map.put("muser01", new UserType(ActivitiType.USER,"user01"));
		map.put("muser01", new UserType(ActivitiType.USER,"user02"));
		
		map.put("muser02", new UserType(ActivitiType.USER,"user02"));
		map.put("muser02", new UserType(ActivitiType.USER,"user03"));
		
		map.put("muser03", new UserType(ActivitiType.USER,"user04"));
		map.put("muser03", new UserType(ActivitiType.USER,"user05"));
		
		map.put("muser04", new UserType(ActivitiType.USER,"user04"));
		
		map.put("muser15", new UserType(ActivitiType.USER,"user11"));
		map.put("muser15", new UserType(ActivitiType.USER,"user12"));
		map.put("muser15", new UserType(ActivitiType.USER,"user13"));
		
		return map;
	}
}
