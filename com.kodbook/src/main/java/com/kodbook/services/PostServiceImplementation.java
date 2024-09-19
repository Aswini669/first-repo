package com.kodbook.services;

import java.util.List;

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

	@Override
	public List<Post> fetchAllPosts() {
		List<Post> allposts = repo.findAll();
		return allposts;
	}

	@Override
	public Post getPost(Long id) {
		Post post= repo.findById(id).get();
		return post;
	}

	@Override
	public void updatePost(Post post) {
		repo.save(post);
		
	}

}
