package it.engineering.spring.mvc.service;

import java.util.ArrayList;
import java.util.List;

import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.dto.UserDto;

public class UserService {
	private List<UserDto> users;
	
	public UserService() {
		users = new ArrayList<UserDto>();
	}
	
	public void add(UserDto userDto) {
		users.add(userDto);
	}
	
	public List<UserDto> getAll() {
		return users;
	}
	
	public UserDto findByName(String firstname) {
		return users.stream().filter(user -> user.getFirstname().equals(firstname)).findAny().orElse(null);
	}
	
	public void delete(String username) {
		UserDto tempUser = null;
		
		for (UserDto user : users) {
			if (user.getUsername().equals(username)) {
				tempUser = user;
			}
		}
		
		if (tempUser != null) {
			users.remove(tempUser);
		}
	}
}
