package com.myapp.repositories;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;

import java.util.List;
import java.util.Arrays;

import com.myapp.models.User;
import com.myapp.repositories.interfaces.IUserRepository;


@Repository("iUserRepository")
public class UserRepository implements IUserRepository<User> {

	RestTemplate restTemplate;

	public User user(Long id) {
		restTemplate = new RestTemplate();
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + id, User.class);
	}

	public List<User> users() {
		restTemplate = new RestTemplate();

		ResponseEntity<User[]> response = 
		restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", User[].class);

		return Arrays.asList(response.getBody());
	}

	public void createUsers(List<User> userList) {
		restTemplate = new RestTemplate();
		HttpEntity<User[]> entity = new HttpEntity<>(userList.toArray(new User[userList.size()]));
		long start = System.currentTimeMillis();
		restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts", entity, User[].class);
		long end = System.currentTimeMillis();
		System.out.println("time to post: " + (end - start));
	}

}
