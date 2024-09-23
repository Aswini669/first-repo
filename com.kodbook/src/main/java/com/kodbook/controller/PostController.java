package com.kodbook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	@Autowired
	PostService pserv;

	@Autowired
	UserService userService;

	@PostMapping("/createPost")
	public String createPost(@RequestParam("caption") String caption, @RequestParam("photo") MultipartFile photo,
	        Model model, HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    User user = userService.getUser(username);

	    Post post = new Post();
	    post.setUser(user);
	    post.setCaption(caption);
	    try {
	        post.setPhoto(photo.getBytes());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    pserv.createPost(post);
	    // update user's posts if user is not null
        List<Post> posts = user.getPosts();
        
	   if (posts == null) {
	            posts = new ArrayList<>();
	    }
	    if(!posts.contains(post))
	    {
	    	posts.add(post);
	    }
	    user.setPosts(posts);
	    userService.updateUser(user);
	    

	    List<Post> allposts = pserv.fetchAllPosts();
	    model.addAttribute("allposts", allposts);
	    return "home";
	}

	@GetMapping("/showPosts")
	public String showPosts(Model model) {
		List<Post> allposts = pserv.fetchAllPosts();
		model.addAttribute("allposts", allposts);
		return "home";
	}

	@PostMapping("/likepost")
	public String likePost(@RequestParam Long id, Model model) {
		Post post = pserv.getPost(id);
		post.setLikes(post.getLikes() + 1);
		pserv.updatePost(post);

		List<Post> allposts = pserv.fetchAllPosts();
		model.addAttribute("allposts", allposts);
		return "home";
	}

	@PostMapping("/addComment")
	public String addComment(@RequestParam Long id, @RequestParam String comment, Model model) {
		Post post = pserv.getPost(id);
		List<String> comments = post.getComments();
		// if the fetched comments is null then it will create an object to resolve the
		// null pointer exception
		if (comments == null) {
			comments = new ArrayList<String>();
		}
		comments.add(comment);
		post.setComments(comments);
		pserv.updatePost(post);

		List<Post> allposts = pserv.fetchAllPosts();
		model.addAttribute("allposts", allposts);
		return "home";
	}
}