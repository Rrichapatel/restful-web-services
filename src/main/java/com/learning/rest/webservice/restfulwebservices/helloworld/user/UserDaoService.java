package com.learning.rest.webservice.restfulwebservices.helloworld.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component //we could use @repository as well. it will be talking to database; here we are just using hardcoded arrays.
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	private static int userCount = 3;
	
	static {
		
		users.add(new User(1, "testuser1", new Date()));
		users.add(new User(2, "testuser2", new Date()));
		users.add(new User(3, "testuser3", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user != null && user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		
		for (User user : users) {
			if (user.getId() == id) {
			return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext())
		{
			User user = iterator.next();
			if (user.getId() == id) 
			{
			iterator.remove();
			return user;
			}
		}
		return null;
		
	}


}
