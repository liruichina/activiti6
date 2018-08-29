package com.abc.springbootactiviti.service;

import java.util.List;

import com.abc.springbootactiviti.beans.UserType;

public interface UserService {

	public List<UserType> getRealUsers(String userId);
	
}
