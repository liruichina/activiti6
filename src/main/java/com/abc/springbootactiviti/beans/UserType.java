package com.abc.springbootactiviti.beans;

import com.abc.springbootactiviti.ActivitiType;

public class UserType {
	
	
	
	public UserType(ActivitiType type, String value) {
		super();
		this.type = type;
		this.value = value;
	}

	private ActivitiType type;
	
	private String value;

	public ActivitiType getType() {
		return type;
	}

	public void setType(ActivitiType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	

}
