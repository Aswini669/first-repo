package com.kodbook.services;

import java.util.List;

import com.kodbook.entities.Post;

public interface PostService {

	public void createPost(Post post);
	
	public List<Post> fetchAllPosts();
	
	public Post getPost(Long id);
	
	public void updatePost(Post post);

}
