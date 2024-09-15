package com.kodbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Post;
import com.kodbook.repository.PostRepository;

@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	PostRepository repo;

	@Override
	public void createPost(Post post) {
		// TODO Auto-generated method stub
		repo.save(post);
		
	}

}
