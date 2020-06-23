package com.spring.AllFiles.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.AllFiles.model.User;
import com.spring.AllFiles.repository.UserRepository;



@Service
public class UserDaoService {
	
	@Autowired
	UserRepository userRepository;

	private static List<User> users = new ArrayList<>();
	
	static { 
		users.add(new User(1,"john",new Date()));
		users.add(new User(2,"PETER",new Date()));
		users.add(new User(3,"parker",new Date()));
		users.add(new User(4,"stefen",new Date()));
	}
	private static int userCount=3;
	
	public List<User> findAll(){
		return users;
	}
	public User save(User user){
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user; 
			}
		}
		return null;
	}
	
	
	public User deleteById(int id) {
		
		Iterator<User> iterator= users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user; 
			}
		}
		return null;
	}
	public User updateUser(Integer id, User user) {
		if(userRepository.findById(id).isPresent()) {
			User presentUser = userRepository.findById(id).get();
			presentUser.setName(user.getName());
			presentUser.setBirthDate(user.getBirthDate());
			
			User updatedUser=userRepository.save(presentUser);
			
			return new User(updatedUser.getId(),updatedUser.getName(),updatedUser.getBirthDate());
		}else {
		return null;
		}
	}
	
}
